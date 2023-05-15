package in.olc.scala.sparkPractice

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkContext

object customerSpending {
  def parseIdAmount(line:String):(Int,Float)={
    val fields=line.split(",")
    val id=fields(0).toInt
    val amount=fields(2).toFloat
    (id,amount)
  }
  def main(args:Array[String]):Unit={
    Logger.getLogger("org").setLevel(Level.ERROR)
    val sc=new SparkContext("local[*]","customer spending")
    val inRDD=sc.textFile("src/main/scala/in/olc/scala/resources/customer-orders.csv")
    val pairedRddIdAmt=inRDD.map(x=>parseIdAmount(x))
    val totalSpent=pairedRddIdAmt.reduceByKey(_+_).sortBy(_._2,false)
    val result=totalSpent.collect()
    result.foreach(println)
  }

}
