package in.olc.scala.sparkPractice

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, initcap, lit, not, regexp_extract, regexp_replace}

object RJVMSparkCommonTypes extends App {
  Logger.getLogger("org").setLevel(Level.ERROR)

  val spark=SparkSession.builder()
    .appName("Spark common types")
    .config("spark.master","local[*]")
    .getOrCreate()

  val moviesDf=spark.read
    .option("inferSchema",true)
    .json("src/main/resources/data/movies.json")

  //adding a plain value to dataframe
  moviesDf.select(col("title"),lit(1).as("constant_value"))//.show()

  //boolean

  val dramaFilter=col("Major_genre") equalTo "Drama"
  val goodRatingFilter=col("IMDB_Rating")>7.0
  val preferredFilter=dramaFilter and goodRatingFilter
val DramaMoviesWithGoodRatingDf=moviesDf.where(preferredFilter).select("title","Major_Genre","IMDB_Rating")
 // DramaMoviesWithGoodRatingDf.show()

  val moviesWithGoodnessFlagDf=moviesDf.select(col("title"),preferredFilter.as("good_movie"))
  //filter with boolean column
  moviesWithGoodnessFlagDf.where("good_movie")//.show //this is equal to where(col("good_movie")==="true")
//negations
  moviesWithGoodnessFlagDf.where(not(col("good_movie")))

  //numbers

  val moviesAvgRatingsDf=moviesDf.select(col("title"),
    (col("Rotten_Tomatoes_Rating")/10 + col("IMDB_Rating"))/2)
 // moviesAvgRatingsDf.show

  //correlation => number between -1 and 1

 // println(moviesDf.stat.corr("Rotten_Tomatoes_Rating","IMDB_Rating")) //corr is an action

  //Strings

  val carsDf=spark.read.option("inferSchema",true).json("src/main/resources/data/cars.json")
  //capitalization => initcap,lower,upper
  carsDf.select(initcap(col("Name")))//.show() //capitalizes first word of the column which we have given

  //contains
  carsDf.select("*").where(col("Name").contains("volkswagen")) //.show()

  // Regex

  val regexString = "volkswagen|vw"
  val vwDF = carsDf.select(
    col("Name"),
    regexp_extract(col("Name"), regexString, 0).as("regex_extract")
  ).where(col("regex_extract") =!= "").drop("regex_extract")//.show()

  vwDF.select(col("Name"),
    regexp_replace(col("Name"),regexString,"people's Car").as("regex_replace"))
   // .show()

  /**
   * Exercise
   *
   * Filter the cars DF by a list of car names obtained by an API call
   * Versions:
   *   - contains
   *   - regexes
   */

  def getNamesOfCars:List[String]=List("Volkswagen", "Mercedes-Benz", "Ford")
  val complexRegex=getNamesOfCars.map(_.toLowerCase()).mkString("|") //"Volkswagen|Mercedes-Benz|Ford"
 // val complexRegex1=List("Volkswagen", "Mercedes-Benz", "Ford").map(_.toLowerCase().mkString("|"))

  carsDf.select(col("Name"),
    regexp_extract(col("name"),complexRegex,0).as("regex_extract")
  ).where(col("regex_extract")=!="")//.show()

  //2
  val carNameFilter=getNamesOfCars.map(_.toLowerCase()).map(name=>col("Name").contains(name))
  val bigFilter = carNameFilter.fold(lit(false))((combinedFilter, newCarNameFilter) => combinedFilter or newCarNameFilter)
  carsDf.filter(bigFilter) //.show
  //println(carNameFilter.mkString(","))


}
