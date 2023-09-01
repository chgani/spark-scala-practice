package in.olc.scala.sparkPractice

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.{Encoders, SparkSession}

import java.sql.Date

object RJVMdataSets extends App {
  Logger.getLogger("org").setLevel(Level.ERROR)
  val spark=SparkSession.builder()
    .appName("Data sets")
    .config("spark.master","local[*]")
    .getOrCreate()

  val numbersDf=spark.read.option("header", true).option("inferSchema",true).csv("src/main/resources/data/numbers.csv")

  implicit val IntEncoder=Encoders.scalaInt //Encoders.scalaInt expression will return encoder of int, has the capability of
  //converting row of data frame to Int
  val numbersDS=numbersDf.as[Int]
  numbersDS.filter(_>100)

  //dataset of a complex class


  case class carsSchema(Acceleration:Double,Cylinders:Long,Displacement:Double,
                        Horsepower:Option[Long],Miles_per_Gallon:Option[Double],Name:String,
                        Origin:String,Weight_in_lbs:Long,Year:Date)
//option is used if null is there it will throw null pointer exceptin without option
  def readDF(filename:String)= spark.read.option("inferSchema", true).json(s"src/main/resources/data/$filename")

 val carsDf=readDF("cars.json")
  carsDf.printSchema()
  import spark.implicits._
  val carsDS=carsDf.as[carsSchema]
  //since dataset is a distributed collection we can have access to use map,flatmap,reduce,fold ... on datasets
  val carsNamesDS=carsDS.map(car=>car.Name.toUpperCase())
  carsNamesDS.show()

}
