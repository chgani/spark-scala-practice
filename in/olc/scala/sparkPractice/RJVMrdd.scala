package in.olc.scala.sparkPractice

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession

import scala.io.Source

object RJVMrdd extends App {
  Logger.getLogger("org").setLevel(Level.ERROR)

  val spark=SparkSession.builder()
    .appName("Spark RDD's")
    .config("spark.master","local[*]")
    .getOrCreate()
  val sc=spark.sparkContext

  //1-Parallelizing existing collection

  val numbers=1 to 10000
  val numbersRDD=sc.parallelize(numbers)

  //2- reading from files
  case class stockValues(company:String,date:String,price:Double)
  def readStocks(filename:String)={
    Source.fromFile(filename).getLines()
      .drop(1)
      .map(line=>line.split(","))
      .map(fields=>stockValues(fields(0),fields(1),fields(2).toDouble)).toList
  }

  val stocksRDD=sc.parallelize(readStocks("src/main/resources/data/stocks.csv"))
  //stocksRDD.foreach(println)

  /*
  //2.1 reading from file
  val stocksRDD1=sc.textFile("src/main/resources/data/stocks.csv")
  //dropping first row
 // val stocksRDD2=stocksRDD1.zipWithIndex().filter{case(_,index)=>index>0}.keys
//  stocksRDD2.foreach(println)
 val stocksRDD3=stocksRDD1
   .map(line=>line.split(",")).map(fields=>stockValues(fields(0),fields(1),fields(2).toDouble))
println(stocksRDD1.getNumPartitions)
  val x= stocksRDD1.glom().collect()
 // x.foreach{array=>array.foreach(println)}

  x.zipWithIndex.foreach { case (partition, index) =>
    println(s"Partition $index:")
    partition.foreach(println)
  }
  */
  // reading from file-2

  val stocksRdd=sc.textFile("src/main/resources/data/stocks.csv")
val stocksRdd1=stocksRdd.map(line=>line.split(","))
  .filter(fields=>fields(0).toUpperCase()==fields(0)) //not for general,depends on dataset and we can use zipwithindex to drop first row

 // stocksRdd1.foreach{arr=>println(arr.mkString(","))}
  val stocksRdd2=stocksRdd1.map(fields=>stockValues(fields(0),fields(1),fields(2).toDouble))
  //stocksRdd2.foreach(println)


  //3 -read from df

  val stocksDF=spark.read.option("inferSchema",true).option("header",true).csv("src/main/resources/data/stocks.csv")
  //val x=stocksDF.rdd.collect()
  //x.foreach{array=>println(array.mkString(","))}
  //if we convert df to rdd directly, we will lose type information. to preserve type information better to follow DF->DS->RDD

  import spark.implicits._
  val stocksDs=stocksDF.as[stockValues]
  val stockRdd=stocksDs.rdd

}
