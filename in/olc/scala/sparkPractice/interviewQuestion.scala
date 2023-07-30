package in.olc.scala.sparkPractice

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object interviewQuestion {
  Logger.getLogger("org").setLevel(Level.ERROR)
  def main(args:Array[String])={
    val sparkconf=new SparkConf().setAppName("practice_Question").setMaster("local[*]")
    val spark=SparkSession.builder().config(sparkconf).getOrCreate()
    val path="C:/Users/ganes/OneDrive/Desktop/New_folder/test.txt"
    val df=spark.read.option("sep",",").option("header","true").csv(path)
    df.show()
    df.printSchema()



//    val lines=spark.read.textFile(path).rdd
//    val header=lines.first()
//    val data=lines.filter(_!=header).map(_.split(","))
//
//    val header_arr=header.split(",")
//import spark.implicits._
//    val df=data.toDF(header_arr:_*)
// df.show()


  }

}
