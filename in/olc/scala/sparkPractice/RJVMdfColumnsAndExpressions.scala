package in.olc.scala.sparkPractice

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col,column,expr,round}

object RJVMdfColumnsAndExpressions extends App {

  Logger.getLogger("org").setLevel(Level.ERROR)
  val spark=SparkSession.builder().appName("columns & expressions").config("spark.master","local[*]").getOrCreate()

  val carsDf=spark.read
    .option("inferSchema",true)
    .json("src/main/resources/data/cars.json")

  //println(carsDf.columns.mkString(","))
  //carsDf.show()

  //columns
  val firstColumn=carsDf.col("Name")

  //selecting
  val carNameDf=carsDf.select(firstColumn)
 // carNameDf.show(false)

 // various select methods => column objects
//there are different ways to select columns (col,column,',$,expr)
  //to use col,column, => we need to import org.apache.spark.sql.functions.{col,column,expr)
  //to use ' and $ => we need to import spark.implicits._

  import spark.implicits._
  carsDf.select(
    carsDf.col("Name"),
    col("Acceleration"),
    column("weight_in_lbs"),
    'Year, // Scala Symbol, auto-converted to column
    $"Horsepower", // fancier interpolated string, returns a Column object
    expr("Origin") // EXPRESSION
  )
    //.show()

  //other ways to select columns => column Strings
  carsDf.select("Name","year")
  //  .show()

  //we cannot use column strings (passing columns as strings)
  // and column objects (col,column,exxpr,',$) on same dataframe

  //Expressions

  val simplestExp=carsDf.col("weight_in_lbs")
  val weightInKg=carsDf.col("weight_in_lbs")/2.2
  val carsWithWeightDf=carsDf.select(col("Name"),col("weight_in_lbs"),
    round(weightInKg,2).as("weight_in_lbs"),
    expr("weight_in_lbs/2.2").as("weight_in_kg_2")
  )
 // carsWithWeightDf.show()

  //************   selectExpr *********************
  val carsWithSelectExprWeightDF=carsDf.selectExpr(
    "Name",
    "weight_in_lbs",
    "weight_in_lbs/2.2 as weight_in_kg"
  )
 // carsWithSelectExprWeightDF.show()

  //*********************** DataFrame Processing **************************
//adding a column
 var carsWithWeightInKgDf= carsDf.withColumn("weight_in_kg_3",col("weight_in_lbs")/2.2)
  //renaming a column
  val carsWithColumnRenamedDf=carsDf.withColumnRenamed("weight_in_lbs","Weight in pounds")
  //carsWithColumnRenamedDf.show()

  //careful with columnn names => when we select a column having spaces in name we need to select that column by following means
  carsWithColumnRenamedDf.selectExpr("`weight in pounds`")
    //.show()

  //remove a column
  carsDf.drop("Name","Acceleration")//.show()

  //filtering
  val ExceptUSAcarsDf=carsDf.filter(col("Origin") =!= "USA")
 // ExceptUSAcarsDf.show()
  //instead of filter we can use where also

  val exceptUSAcarsDf1=carsDf.where(col("origin")=!="USA")
  //exceptUSAcarsDf1.show()

  //filtering with strinng expressions

  val exceptUSAcarsDf2=carsDf.filter("Origin = 'USA'")

  // chain filters
  val americanPowerFullCarsDf=carsDf.filter(col("Origin") === "USA")
    .filter(col("Horsepower")>150)
 // americanPowerFullCarsDf.show()

  val americanPowerFullCarsDf2=carsDf.filter(col("Origin")==="USA" && col("HorsePower")>150)
  //americanPowerFullCarsDf2.show() //(in place of && we can use and also)

  val americanPowerFullCarsDf3=carsDf.filter("Origin='USA' and Horsepower>150")
  //americanPowerFullCarsDf3.show()

  //union => adding more rows
  val moreCarsDF=spark.read
    .option("inferSchema",true)
    .json("src/main/resources/data/more_cars.json")

  val allCarsDf=carsDf.union(moreCarsDF) //works if both dataframes have same schema

  //distinct values
  val allCarsDistinctDf=allCarsDf.distinct()
//  allCarsDistinctDf.show()
//  println(s"the number of records in carsDF=${carsDf.count()} and \n" +
//    s"the number of records in moreCarsDF =${moreCarsDF.count()} and \n" +
//    s"the number of in allCarsDf after union =${allCarsDf.count()} \n" +
//    s"the number of distinct records in allCarsDf =${allCarsDistinctDf.count()}")

  val allCountryDf=allCarsDf.select("Origin").distinct()
//  allCountryDf.show()

  /**
   * Exercises
   *
   * 1. Read the movies DF and select 2 columns of your choice
   * 2. Create another column summing up the total profit of the movies = US_Gross + Worldwide_Gross + DVD sales
   * 3. Select all COMEDY movies with IMDB rating above 6
   *
   * Use as many versions as possible
   */

  val moviesDf=spark.read
    .option("inferSchema",true)
   // .option("nullValue",0)
    .json("src/main/resources/data/movies.json")
moviesDf.printSchema()

  val moviesDf1=moviesDf.select("Creative_Type","Director")
 // moviesDf1.show()

  val moviesDf3=moviesDf.select("Creative_Type","Director","US_Gross","Worldwide_Gross","US_DVD_Sales")
//  moviesDf3.show()\
  val moviesDf4=moviesDf.selectExpr("Creative_Type","Director","US_Gross","Worldwide_Gross","US_DVD_Sales",
  "US_Gross + Worldwide_Gross as profit")
//  moviesDf4.show()

//  val moviesProfitDF = moviesDF.select(
//    col("Title"),
//    col("US_Gross"),
//    col("Worldwide_Gross"),
//    col("US_DVD_Sales"),
//    (col("US_Gross") + col("Worldwide_Gross")).as("Total_Gross")
//  )

  val moviesDf5=moviesDf.select("Title","Major_Genre","IMDB_Rating")
  //moviesDf5.show()

  val comedyMoviesDf=moviesDf5.filter("Major_genre = 'Comedy' and IMDB_Rating >6")
//  //comedyMoviesDf.show(false)
//
//  val moviesProfitDF3 = moviesDf.select("Title", "US_Gross", "Worldwide_Gross")
//    .withColumn("Total_Gross", col("US_Gross") + col("Worldwide_Gross"))
//
//  // 3
//  val atLeastMediocreComediesDF = moviesDf.select("Title", "IMDB_Rating")
//    .where(col("Major_Genre") === "Comedy" and col("IMDB_Rating") > 6)
//
//  val comediesDF2 = moviesDf.select("Title", "IMDB_Rating")
//    .where(col("Major_Genre") === "Comedy")
//    .where(col("IMDB_Rating") > 6)
//
//  val comediesDF3 = moviesDf.select("Title", "IMDB_Rating")
//    .where("Major_Genre = 'Comedy' and IMDB_Rating > 6")



}
