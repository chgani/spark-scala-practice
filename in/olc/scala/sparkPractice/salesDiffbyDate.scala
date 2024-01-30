package in.olc.scala.sparkPractice

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, to_date}

object salesDiffbyDate {
  def main(args:Array[String])= {
    Logger.getLogger("org").setLevel(Level.ERROR)
    val conf = new SparkConf().setAppName("diff").setMaster("local[*]")
    val spark = SparkSession.builder().config(conf).getOrCreate()
    val data = Seq(("2022-01-01", "apples", 20), ("2022-01-01", "oranges", 18),
      ("2022-01-02", "apples", 15), ("2022-01-02", "oranges", 16),
      ("2022-01-03", "apples", 5), ("2022-01-03", "oranges", 0),
      ("2022-01-04", "apples", 20), ("2022-01-04", "oranges", 21))

    val schema = List("date", "name", "sales")
    import spark.implicits._
    val df1 = data.toDF(schema: _*)
    val df2=df1.withColumn("date",to_date(col("date"),"yyyy-MM-dd"))
    val apple_df=df2.where(col("name")==="apples").select(col("date"),col("sales").as("apple_sales"))
   val orange_df=df2.where(col("name")==="oranges").select(col("date"),col("sales").as("orange_sales"))
    val joined_df=apple_df.join(orange_df,apple_df("date")===orange_df("date"),"inner").drop(orange_df("date"))
joined_df.show()
    val diff_df=joined_df.withColumn("diff",col("apple_sales")-col("orange_sales")).select("date","diff")
    diff_df.show()
  }
}
