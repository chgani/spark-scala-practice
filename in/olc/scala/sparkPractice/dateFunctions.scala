package in.olc.scala.sparkPractice

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object dateFunctions {
  def main(args:Array[String])={
    Logger.getLogger("org").setLevel(Level.ERROR)
    val spark=SparkSession.builder.appName("date functions").master("local[*]").getOrCreate()
    import spark.implicits._
    val stockList = Seq(("PAYTM", "01-01-2022"), ("PAYTM", "02-01-2022"), ("PAYTM", "03-01-2022"),
      ("PAYTM", "04-01-2022"), ("PAYTM", "05-01-2022"), ("LIC", "01-01-2022"), ("LIC", "02-01-2022"),
      ("LIC", "03-01-2022"), ("LIC", "04-01-2022"))
    val df1=stockList.toDF("company","date")
    val df2=df1.select(col("company"),to_date(col("date"),"dd-MM-yyyy").as("date"))
    df2.select(date_format(col("date"),"MM-dd-yyyy")).show()
    df2.printSchema()
    df2.show()

  }

}
