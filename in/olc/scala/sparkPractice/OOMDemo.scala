package in.olc.scala.sparkPractice

import org.apache.spark.sql.SparkSession

object OOMDemo {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder.appName("OOMExample").master("local[*]").getOrCreate()

    // Create a large dataframe with a single column of integers.
    val df = spark.range(100000000).toDF("value")

    // Try to collect the dataframe. This will cause an OOM error.
    df.collect()
  }

}
