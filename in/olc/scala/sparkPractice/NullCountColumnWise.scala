package in.olc.scala.sparkPractice

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object NullCountColumnWise {

  def main(args:Array[String]):Unit={
    Logger.getLogger("org").setLevel(Level.ERROR)
    val conf=new SparkConf().setAppName("Null count").setMaster("local[*]")
    val spark=SparkSession.builder().config(conf).getOrCreate()

//    val data1 = Seq(
//      (1, "Alice", 28),
//      (2, "Bob", 35),
//      (3,"Charlie", 40),
//      (4, "David", 33)
//    )
//    import spark.implicits._
//    val cols=List("id","name","age")
//    val df_nu=data1.toDF(cols:_*)
//    df_nu.show()

    val df=spark.read.options(Map("header" -> "true","inferSchema"->"true")).csv("C:/Users/ganes/Downloads/nullcount.csv")
   // df.show()

    //val nullCounts=df.select(df.columns.map(c=>count(col(c).isNull.cast("int")).alias(c)):_*)
   val nullCounts = df.select(df.columns.map(c => sum(col(c).isNull.cast("int")).alias(c)): _*)
    //nullCounts.show()


  }

}
