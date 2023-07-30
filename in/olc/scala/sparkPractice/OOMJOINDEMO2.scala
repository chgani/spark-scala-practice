package in.olc.scala.sparkPractice

import org.apache.spark.sql.SparkSession

object OOMJOINDEMO2 {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder.appName("OOMExample").master("local[*]").getOrCreate()

    // Create two large dataframes with a single column of integers.
    val df1 = spark.range(100000000).toDF("value")
    val df2 = spark.range(100000000).toDF("value")

    // Try to join the two dataframes. This will cause an OOM error.
    df1.join(df2, "value").collect()

  }
}
