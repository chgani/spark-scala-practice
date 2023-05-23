package in.olc.scala.sparkPractice

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, rand}


object randomNumbers {
  def main(args:Array[String]):Unit={
    Logger.getLogger("org").setLevel(Level.ERROR)
    val conf=new SparkConf().setAppName("groupNumbers").setMaster("local[*]")
    val spark=SparkSession.builder().config(conf).getOrCreate()
   // val randomNumdf=spark.range(1500).selectExpr("rand() as numbers")
   // val randomNumdf2=randomNumdf.withColumn("Numbers", col("numbers").cast("string"))
//    randomNumdf2.repartition(1).select("Numbers").write.format("text").save("C:/edu/olc/randomNumbers")
    val numDf=spark.range(1,1500).toDF("numbers").orderBy(rand())
    numDf.repartition(1).select(col("Numbers").cast("string")).write.mode("overWrite")
      .format("text").save("C:/edu/olc/randomNum")

  }

}
