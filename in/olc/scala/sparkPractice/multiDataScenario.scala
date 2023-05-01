package sparkPractice

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object multiDataScenario {
  def main(args:Array[String]):Unit={
    Logger.getLogger("org").setLevel(Level.ERROR)
    val conf=new SparkConf().setAppName("multidataSparkScenario").setMaster("local[*]")
    val spark=SparkSession.builder().config(conf).getOrCreate()
    val in_df=spark.read.format("csv").option("header",true).option("delimiter","|").load("src/main/resources/multidata.txt")
   println("-----------------in_df-----------------------------")
    in_df.printSchema()
    in_df.show(false)
    val df2=in_df.withColumn("subject",explode(split(in_df("Education"),","))).drop("Education")
    println("-----------------df2-----------------------------")//explode will not show null record
    df2.printSchema()
    df2.show()

    println("-----------------df3-----------------------------")
    val df3 = in_df.withColumn("subject", explode_outer(split(in_df("Education"), ","))).drop("Education")
    df3.printSchema()  //will show null record
    df3.show()
    println("-----------------poseexplode-----------------------------")
    in_df.select(posexplode(split(in_df("Education"),","))).show()

    println("-----------------poseesxplode_outer-----------------------------")
    in_df.select(posexplode_outer(split(in_df("Education"),","))).show()

  }

}
