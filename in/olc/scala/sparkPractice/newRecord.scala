package in.olc.scala.sparkPractice

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{col, row_number, to_date}
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}

object newRecord {
  Logger.getLogger("org").setLevel(Level.ERROR)
  def main(args:Array[String])={
    val config= new SparkConf().setMaster("local[*]").setAppName("new record for each name")
    val spark=SparkSession.builder().config(config).getOrCreate()
    val data = Seq((1, "Arul", "Chennai", "2023-01-01"),
      (1, "Arul", "Bangalore", "2023-02-01"),
      (2, "Sam", "Chennai", "2023-01-01"),
      (3, "manish", "patna", "2023-01-01"),
      (3, "manish", "patna", "2023-03-15"),
      (3, "manish", "patna", "2023-02-27"))

    val schema = new StructType(Array(
      StructField("id", IntegerType, true),
      StructField("name", StringType, true),
      StructField("location", StringType, true),
      StructField("date", StringType, true)))
      val rows=data.map{case(id,name,location,date)=>Row(id,name,location,date)}
val rdd=spark.sparkContext.parallelize(rows)
    val df_input=spark.createDataFrame(rdd,schema)

    val p_df=df_input.withColumn("date",to_date(col("date"),"yyyy-MM-dd"))
    val windowFun=Window.partitionBy("id").orderBy(col("date").desc)

    val p_df2=p_df.withColumn("rank",row_number() over(windowFun)).where("rank==1")
      .select("id","name","location","date").orderBy("id")
    p_df2.show()
  }

}
