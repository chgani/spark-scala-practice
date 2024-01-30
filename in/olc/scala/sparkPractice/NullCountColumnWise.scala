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

    val data1 = Seq(
      (1, Some("Alice"), Some(28)),
      (2, None, Some(35)),
      (3,Some("Charlie"), Some(40)),
      (4, Some("David"), Some(33)),
        (5,Some("Sammy"),None),
      (6,None,None)
    )
    import spark.implicits._
    val cols=List("id","name","age")
    val df_nu=data1.toDF(cols:_*)
    df_nu.show()

    val nullCounts1 = df_nu.select(df_nu.columns.map(c => sum(col(c).isNull.cast("int")).alias(c)): _*)
    nullCounts1.show()

//    val df=spark.read.options(Map("header" -> "true"0
//
//    //val nullCounts=df.select(df.columns.map(c=>count(col(c).isNull.cast("int")).alias(c)):_*)
//   val nullCounts = df.select(df.columns.map(c => sum(col(c).isNull.cast("int")).alias(c)): _*)
//    nullCounts.show()

  }

}
