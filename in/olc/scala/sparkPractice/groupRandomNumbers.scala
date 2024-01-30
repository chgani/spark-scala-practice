package in.olc.scala.sparkPractice

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object groupRandomNumbers {
def main(args:Array[String])={
  Logger.getLogger("org").setLevel(Level.ERROR)

  val conf=new SparkConf().setAppName("groupNum").setMaster("local[*]")
  val spark=SparkSession.builder().config(conf).getOrCreate()

  val in_rdd=spark.sparkContext.textFile("c:/edu/olc/randNum/numbers.txt")

  val process_rdd=in_rdd.flatMap(_.split(","))

  import spark.implicits._

  val in_df=process_rdd.toDF("Number")

  val divide_df=in_df.withColumn("group_name",
    when(col("Number")<=100,"g1")
  .when(col("Number")<=200 && col("Number")>100,"g2")
  .when(col("Number")<=300 && col("Number")>200,"g3"))

  val group_df=divide_df.groupBy("group_name")
    .agg(collect_list(col("Number")).alias("group_numbers"))

  val with_size_df=group_df.withColumn("size",size(col("group_numbers")))
    //.agg(max(col("size")).alias("max_size"))

  val max_group_df=with_size_df.orderBy(desc("size"))
  max_group_df.show(1)

}
}
