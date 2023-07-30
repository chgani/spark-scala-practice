package in.olc.scala.sparkPractice

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, rand}
import org.apache.spark.sql.types.IntegerType


object randomNumbers {
  def main(args:Array[String]):Unit={
    Logger.getLogger("org").setLevel(Level.ERROR)
    val conf=new SparkConf().setAppName("groupNumbers").setMaster("local[*]")
    val spark=SparkSession.builder().config(conf).getOrCreate()
   // val randomNumdf=spark.range(1500).selectExpr("rand() as numbers")
   // val randomNumdf2=randomNumdf.withColumn("Numbers", col("numbers").cast("string"))
//    randomNumdf2.repartition(1).select("Numbers").write.format("text").save("C:/edu/olc/randomNumbers")
    val numDf=spark.range(1,1500).toDF("numbers").orderBy(rand())
//    numDf.repartition(1).select(col("Numbers").cast("string")).write.mode("overWrite")
//      .format("text").save("C:/edu/olc/randomNum")

    val num_range=numDf.withColumn("num_group",((col("numbers")-1)/100).cast(IntegerType))
    val numDfWithString = num_range.withColumn("numbers_str", col("numbers").cast("string"))

    numDfWithString.select("numbers_str","num_group").write.partitionBy("num_group").text("C:/edu/olc/randomNum1")
println("completed")
  }

}
