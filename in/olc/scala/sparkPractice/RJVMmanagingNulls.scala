package in.olc.scala.sparkPractice

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{coalesce, col}

object RJVMmanagingNulls extends App {
  Logger.getLogger("org").setLevel(Level.ERROR)

  val spark=SparkSession.builder()
    .appName("Managing Nulls")
    .config("spark.master","local[*]")
    .getOrCreate()

  val moviesDf=spark.read.option("inferSchema",true).json("src/main/resources/data/movies.json")

  //select first non-null value
  moviesDf.select(col("Title"),
    col("Rotten_Tomatoes_Rating"),
    col("IMDB_Rating"),
    coalesce(col("Rotten_Tomatoes_Rating"),col("IMDB_Rating")*10))
    //.show()

  //checking for nulls
  moviesDf.select("*").where(col("Rotten_Tomatoes_Rating").isNull)

  //nulls when ordering
  moviesDf.select("*").orderBy(col("IMDB_Rating").desc_nulls_last)

  //removing nulls
  moviesDf.select("Title","IMDB_Rating").na.drop() //removes rows containing nulls

  //replace nulls
  moviesDf.na.fill(0,List("IMDB_Rating","Rotten_Tomatoes_rating"))
    .na.fill("test",List("Creative_Type"))//.show()

  // another version
  moviesDf.na.fill(Map(
    "IMDB_Rating" -> 0,
    "Rotten_Tomatoes_Rating" -> 0,
    "Creative_Type" -> "Unknown",
    "Director" ->"UnKnown"
  ))//.show()

  //complexOperations
  moviesDf.selectExpr("Title","Rotten_Tomatoes_Rating","IMDB_Rating",
  "ifnull(Rotten_Tomatoes_Rating,IMDB_Rating*10) as ifnull", //same as coalesce first value is null it will return second value
"nvl(Rotten_Tomatoes_Rating,IMDB_Rating*10) as nvl", //same as coalesce
    "nullif(Rotten_Tomatoes_Rating,IMDB_Rating*10) as nullif", //returns null if both columns are equal, else first value
    "nvl2(Rotten_Tomatoes_Rating,IMDB_Rating*10,0.0) as nvl2" // if(first!=null) second else third
  )//.show()

}
