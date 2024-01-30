package in.olc.scala.sparkPractice

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{collect_list, concat_ws, rand}

object generateRandomNumbers {
  def main(args: Array[String]) = {
    Logger.getLogger("org").setLevel(Level.ERROR)
    val conf = new SparkConf().setAppName("random").setMaster("local[*]")
    val spark = SparkSession.builder().config(conf).getOrCreate()

    val randomNumbers = spark.range(100) // Generate 10 random numbers
      .withColumn("random_number", (rand() * 300 + 1).cast("int").cast("string"))
    val randomNumbers1=randomNumbers.coalesce(1)
    randomNumbers1
      .agg(concat_ws(",",collect_list("random_number")).alias("random_numbers"))
      .select("random_numbers")
      .write
      .mode("overwrite")
      .option("header", "false")
      .text("C:/edu/olc/randNum/")


    spark.stop()

  }
}
