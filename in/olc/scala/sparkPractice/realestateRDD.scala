package in.olc.scala.sparkPractice

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object realestateRDD {

//  def parser(line:String)={
//    val fields=line.split("|")
//    (fields(0),fields(1))
//  }

  def main(args: Array[String]) = {
    Logger.getLogger("org").setLevel(Level.ERROR)
    val conf = new SparkConf().setAppName("rdd").setMaster("local[*]")
    val spark=SparkSession.builder().config(conf).getOrCreate()

    val rdd1=spark.sparkContext.textFile("C:/edu/practice/DATASETS/realestate.txt")

    rdd1.foreach(println)
    val filterRdd=rdd1.zipWithIndex().filter{case(line,index)=>index>0}.map{case(li,in)=>li}

    println("*****************************************")
filterRdd.foreach(println)
    val final_rdd=filterRdd.map{x=> {
      val fields=x.split("|")
      (fields(0),fields(1))
    }}
    final_rdd.foreach(println)


  }
}
