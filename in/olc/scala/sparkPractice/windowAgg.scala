package in.olc.scala.sparkPractice

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions._

object windowAgg {
  def main(args:Array[String])={
  Logger.getLogger("org").setLevel(Level.ERROR)
  val conf = new SparkConf().setAppName("Window Aggregations").setMaster("local[*]")
  val spark = SparkSession.builder().config(conf).getOrCreate()
  //calculating running total  for each product

  import spark.implicits._

//  val salesData = Seq(
//    ("Product1", "Category1", 100),
//    ("Product2", "Category2", 200),
//    ("Product3", "Category1", 150),
//    ("Product4", "Category3", 300),
//    ("Product5", "Category2", 250),
//    ("Product6", "Category3", 180)
//  ).toDF("Product", "Category", "Revenue")
//
//  val winFun = Window.partitionBy("Category").orderBy("Revenue")
//  val df = salesData.select(col("*"),sum("Revenue").over(winFun).as("Running total"))
//  //df.show()
//
////Finding the maximum revenue for each product category and the corresponding product.
//    val df_max=salesData.select(col("category"),col("Revenue"),max("Revenue").over(winFun).as("maxRevenue"))
//df_max.show()

    //Calculating the average rating for each user
//    val ratingData = Seq(
//      ("User1", "Movie1", 4.5),
//      ("User1", "Movie2", 3.5),
//      ("User1", "Movie3", 2.5),
//      ("User1", "Movie4", 4.0),
//      ("User1", "Movie5", 3.0),
//      ("User1", "Movie6", 4.5),
//      ("User2", "Movie1", 3.0),
//      ("User2", "Movie2", 4.0),
//      ("User2", "Movie3", 4.5),
//      ("User2", "Movie4", 3.5),
//      ("User2", "Movie5", 4.0),
//      ("User2", "Movie6", 3.5)
//    ).toDF("User", "Movie", "Rating")
//
//    val winFun=Window.partitionBy("User")//.orderBy("Movie")
//    val df_avg=ratingData.select(col("User"),avg("Rating").over(winFun).as("AvgRating"))
//    df_avg.show()

    //Calculate  the moving or running average of revenue for each product
    val salesData = Seq(
      ("Product1", 100),
      ("Product2", 150),
      ("Product1", 200),
      ("Product3", 180),
      ("Product1", 250),
      ("Product4", 300),
      ("Product1", 100),
      ("Product6", 150),
      ("Product1", 200),
      ("Product5", 180),
      ("Product1", 250),
      ("Product6", 300)
    ).toDF("Product", "Revenue")
//,sum(col("Revenue").over(winspec).as("runningTotal"))
    println(salesData.rdd.getNumPartitions)
 //   println(400/3)
    val winspec=Window.partitionBy("Product").orderBy("Revenue")
    val df=salesData.select(col("Product"),col("Revenue"),
      avg(col("Revenue")).over(winspec).as("Running Average"))
  val df1=  df.coalesce(3)
    df1.sort(col("Product")).show()
  // Thread.sleep(600000)
}
}
