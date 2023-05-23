package in.olc.scala.sparkPractice

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object complexJson {
  def main(args: Array[String]): Unit = {
    Logger.getLogger("org").setLevel(Level.ERROR)
    val conf = new SparkConf().setAppName("Json Exercise").setMaster("local[*]")
    val spark = SparkSession.builder().config(conf).getOrCreate()
    val in = spark.read.option("multiline",true).json("C:/edu/olc/Orchestra.json")
    in.printSchema()

  }
}