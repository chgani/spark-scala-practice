package in.olc.scala.sparkPractice

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object RJVMdataFrameAggregations extends App {
  Logger.getLogger("org").setLevel(Level.ERROR)

  val spark=SparkSession.builder().appName("DataFrame Aggregations").config("spark.master","local[*]").getOrCreate()

  val moviesDF=spark.read
    .option("inferSchema",true)
    .json("src/main/resources/data/movies.json")

  //counting
  val genresCountDf=moviesDF.select(count("Major_Genre").as("GenresCount")) //all the values except count
  //val genresCountDf=moviesDF.select(count(col("Major_Genre")).as("GenresCount"))
  // genresCountDf.show()

  moviesDF.selectExpr("count(Major_Genre) as genreCount")
  moviesDF.select(count("*")) //counts all the rows including nulls

  //countinng distinct
  moviesDF.select(countDistinct("Major_Genre"))//.show()

  //approximate count (will be use ful when we deal with large datasets)
  moviesDF.select(approx_count_distinct("Major_Genre"))

  //min and max

  moviesDF.select(max(col("IMDB_Rating")))
  moviesDF.selectExpr("min(IMDB_Rating)")

  //sum
  moviesDF.select(sum(col("US_Gross")))
  moviesDF.selectExpr("sum(US_Gross)")

  // avg
  moviesDF.select(avg(col("Rotten_Tomatoes_Rating")))
  moviesDF.selectExpr("avg(Rotten_Tomatoes_Rating)")

  //data science

  moviesDF.select(
    mean(col("Rotten_Tomatoes_Rating")),
    stddev(col("Rotten_Tomatoes_Rating"))
  )

  //grouping
  val countByGenreDf=moviesDF.groupBy("Major_Genre").count() //includes nulls
  //select count(*) from moviesDF group by Major_Genre
  //countByGenreDf.show()

  val avgRatingByGenreDf=moviesDF
    .groupBy("Major_Genre")
    .avg("IMDB_Rating")
  //avgRatingByGenreDf.show()

val aggregationByGenreDf=moviesDF
  .groupBy(col("Major_Genre"))
  .agg(
    count("*").as("Movies_Count"),
    avg("IMDB_Rating").as("Avg_Rating")
  )
  .orderBy(col("Avg_Rating"))

 // aggregationByGenreDf.show()

  /**
   * Exercises
   *
   * 1. Sum up ALL the profits of ALL the movies in the DF
   * 2. Count how many distinct directors we have
   * 3. Show the mean and standard deviation of US gross revenue for the movies
   * 4. Compute the average IMDB rating and the average US gross revenue PER DIRECTOR
   */

  moviesDF.printSchema()
  //moviesDF.show()

  val sumOfProfitsDf=moviesDF.selectExpr("US_DVD_Sales+US_Gross+Worldwide_Gross as sales")
    .select(sum("sales").as("Total_Sales"))
  //sumOfProfitsDf.show()

  val countOfDirectorsDf=moviesDF.select(countDistinct("Director").as("Directors_count"))
 // countOfDirectorsDf.show()


  val MeanSDmoviesDf=moviesDF.groupBy("Title")
    .agg(
      mean("US_Gross").as("Avg_Gross_Per_movie"),
      stddev("US_Gross").as("StdDev_of_Gross_per_movie")
    ).select("Title","Avg_Gross_Per_movie","StdDev_of_Gross_per_movie")
  //MeanSDmoviesDf.show()

  val avgGrossAvgRatingDf=moviesDF.groupBy("Director")
    .agg(
      avg("US_Gross").as("Avg_Gross"),
      avg("IMDB_Rating").as("Avg_Rating")
    )
    //.select("Director","Avg_Gross","Avg_Rating")
    .orderBy(col("Avg_Rating").desc_nulls_last)
 // avgGrossAvgRatingDf.show()





}
