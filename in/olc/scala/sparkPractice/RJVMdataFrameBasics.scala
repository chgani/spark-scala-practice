package in.olc.scala.sparkPractice

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object RJVMdataFrameBasics extends App{
  Logger.getLogger("org").setLevel(Level.ERROR)

  val conf=new SparkConf().setAppName("Dataframe Basics").setMaster("local[*]")
  val spark=SparkSession.builder().config(conf).getOrCreate()

  val df=spark.read.format("json").load("src/main/resources/data/cars.json")
  df.printSchema()
 // df.show()

//  val df1 = spark.read.format("json").option("inferSchema",true).load("src/main/resources/data/cars.json")
//  df.printSchema()
//  df.show()

  //show df as rows
  df.take(10).foreach(println)

//to get the schema of existing data frame in struct type format
  val carSchema=df.schema
  println(carSchema)

  // creating a df by seq

//  val smartphones = Seq(
//    ("Samsung", "Galaxy S10", "Android", 12),
//    ("Apple", "iPhone X", "iOS", 13),
//    ("Nokia", "3310", "THE BEST", 0)
//  )
//
//  import spark.implicits._
//  val smartphonesDF = smartphones.toDF("Make", "Model", "Platform", "CameraMegapixels")
//  smartphonesDF.show()


  //movies dataframe

  val moviesDF=spark.read.json("src/main/resources/data/movies.json")
  moviesDF.printSchema()
  //moviesDF.show()

  val countOfRows=moviesDF.count()
  println(s"the number of rows in movies dataframe are $countOfRows")


}
