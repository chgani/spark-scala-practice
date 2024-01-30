package in.olc.scala.sparkPractice

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object salesDateWise {
  Logger.getLogger("org").setLevel(Level.ERROR)
  def main(args:Array[String])={
    val conf=new SparkConf().setAppName("sales data").setMaster("local[*]")
    val spark=SparkSession.builder().config(conf).getOrCreate()
    val data=Seq((1,"LCPHONE ","2000-01-16"),(2," LCphone","2000-01-17"),(3,"LcPhOne","2000-02-18"),
      (4,"   LCKeyChAIn  ","2000-02-19"),(5,"LCKeyChain   ","2000-02-28"),(6,"MatryoShka ","2000-03-31"))

    val schema=List("sale_id","product_name","sale_date")

    val df=spark.createDataFrame(spark.sparkContext.parallelize(data)).toDF(schema:_*)
    df.show()

//    val df1=df.select(lower(trim(col("product_name"))).as("product_name"),
//      date_format(to_date(col("sale_date"),"yyyy-MM-dd"),"yyyy-MM").as("sale_date"))
val df1 = df.select(lower(trim(col("product_name"))).as("product_name"),
  date_format(col("sale_date"), "yyyy-MM").as("sale_date"))

    df1.show()
    val df2=df1.groupBy("product_name","sale_date").count().
      orderBy(desc("count"),col("product_name"))

    df2.show()
  }

}
