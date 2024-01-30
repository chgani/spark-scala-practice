package in.olc.scala.sparkPractice

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, explode}

object githubdata {
  def main(args:Array[String])={
    Logger.getLogger("org").setLevel(Level.ERROR)

    val sparkconf=new SparkConf().setAppName("github data").setMaster("local[*]")
    val spark=SparkSession.builder().config(sparkconf).getOrCreate()

    val in_df=spark.read.json("C:/edu/1DataEngineeringusingAWSAnalyticsServices/data/extract/2021-01-13*")
//    in_df.printSchema()
//    in_df.show()

    val p_df1=in_df.select(col("payload.comment.performed_via_github_app.events").alias("events")).filter(col("events").isNotNull)
val p_df2=p_df1.select(explode(col("events")))
    p_df2.printSchema()
    p_df2.show()

  }

}
