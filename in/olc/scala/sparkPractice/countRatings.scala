package in.olc.scala.sparkPractice

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkContext

//count records of each ratings
object countRatings {
  def main(args:Array[String]):Unit={
    Logger.getLogger("org").setLevel(Level.ERROR)
    val sc=new SparkContext("local[*]","Ratings counter")
    val inData=sc.textFile("src/main/scala/in/olc/scala/resources/movieLense.data")
    val ratings=inData.map(line=>line.split("\t")(2))
    println("By Method 1")
    val ratingTuple=ratings.map(x=>(x,1))
    val result=ratingTuple.reduceByKey(_+_).sortBy(_._2,false)
    result.foreach(println)
    println("\nBy method 2")
    val ratingCount1=ratings.countByValue()
    val result1=ratingCount1.toSeq.sortBy(_._2)
    //here sortBy will not take second parameter because we are applying sortBy on sequence,inorder to
    //use second parameter in sortBy we need to parallelize the seq
    result1.foreach(println)
  }

}
