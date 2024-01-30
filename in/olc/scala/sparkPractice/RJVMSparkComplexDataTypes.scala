package in.olc.scala.sparkPractice

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object RJVMSparkComplexDataTypes  extends App {
  Logger.getLogger("org").setLevel(Level.ERROR)
  val spark=SparkSession.builder()
    .appName("spark complex data types")
    .config("spark.master","local[*]")
    .getOrCreate()

  val movieDf=spark.read.option("inferSchema",true).json("src/main/resources/data/movies.json")
movieDf.printSchema()
  movieDf.show()
  //Dates =>date_add,date_sub
 val moviesWithDateDf= movieDf.select(col("Title"),to_date(col("Release_Date"),"dd-MMM-yy").as("Actual_Release"))
//  dateDf.show()
//  dateDf.printSchema()
    .withColumn("Today",current_timestamp())
    .withColumn("Right_now",current_timestamp())
    .withColumn("Age_of_Movie",datediff(col("Today"),col("Actual_Release"))/365)

  //in some cases the format of the date column which we have passes in to_date will be different than other.
  //in that cases , it will show that value as null

  moviesWithDateDf.select("*").where(col("Actual_Release").isNull)//.show()

  /**
   * Exercise
   * 1. How do we deal with multiple date formats?
   * 2. Read the stocks DF and parse the dates
   */

  //1 - parse the DF multiple times, then union the small DFs => which is not recommended

  //2

  val stocksDf=spark.read
    .option("header",true)
    .option("inferSchema",true).csv("src/main/resources/data/stocks.csv")
//  stocksDf.show()
//  stocksDf.printSchema()
stocksDf.withColumn("Actual_date",to_date(col("date"),"MMM d yyyy"))
  //.where(col("Actual_date").isNull).show()

  //structures

  // 1. With col operators

  movieDf.select(col("Title"),struct(col("US_Gross"),col("Worldwide_Gross")).as("Profit"))
    .select(col("Title"),col("Profit"),col("Profit").getField("US_Gross").as("US_Profit"))
  //.show()

  // 2. with expression strings

  movieDf.selectExpr("Title","(US_Gross,Worldwide_Gross) as Profit")
    .selectExpr("Title","Profit","Profit.US_Gross as US_Profit")
   // .show()

  val moviesWithWordsDf=movieDf.select(col("Title"),split(col("Title")," |,").as("Title_Words"))
  //moviesWithWordsDf.show()
  moviesWithWordsDf.select(col("Title"),expr("Title_Words[0]"),
    size(col("Title_Words")),array_contains(col("Title_Words"),"Love")).show()

}
