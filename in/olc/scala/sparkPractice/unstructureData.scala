package in.olc.scala.sparkPractice

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types.{StringType, StructField, StructType}

object unstructureData {
  Logger.getLogger("org").setLevel(Level.ERROR)

  def main(args: Array[String]) = {
    val conf = new SparkConf().setAppName("unstructured data").setMaster("local[*]")
    val spark = SparkSession.builder().config(conf).getOrCreate()
    val in = spark.sparkContext.textFile("src/main/resources/data/unstructured.txt")
    val schema = new StructType().add(StructField("column", StringType, true))
    val processedRDD = in.flatMap(line => line.grouped(10).map(chunk => Row(chunk)))
    val p1=in.map(x=>x.grouped(10))
    p1.foreach(println)
    val df = spark.createDataFrame(processedRDD, schema)
   // df.show(false)
  }

}
