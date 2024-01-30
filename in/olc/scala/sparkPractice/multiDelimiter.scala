package in.olc.scala.sparkPractice

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{col, monotonically_increasing_id, row_number, split}

object multiDelimiter {
  def main(args: Array[String]) = {
    Logger.getLogger("org").setLevel(Level.ERROR)
    val conf = new SparkConf().setAppName("multi delimiter").setMaster("local[*]")
    val spark = SparkSession.builder().config(conf).getOrCreate()
    val df1=spark.read.textFile("C:/edu/practice/DATASETS/multiDelim.txt")
df1.show()
    val df2=df1.withColumn("name_age",split(col("value"),"~\\|"))
    val windowFun=Window.orderBy(monotonically_increasing_id())
   val df3=df2.withColumn("row_num",row_number().over(windowFun)).filter(col("row_num")>1).drop(col("row_num"))
val df4=df3.select(col("name_age")(0).alias("Name"),col("name_age")(1).alias("Age"))
    df4.show()
  }

}
