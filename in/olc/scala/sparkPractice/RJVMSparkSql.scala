package in.olc.scala.sparkPractice

import org.apache.spark.sql.SparkSession
import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.functions.col

object RJVMSparkSql extends App {
  Logger.getLogger("org").setLevel(Level.ERROR)
val spark=SparkSession.builder()
  .appName("spark Sql")
  .config("spark.master","local[*]")
  .getOrCreate()

  val carsDf=spark.read.option("inferSchema",true).json("src/main/resources/data/cars.json")

  // by dataframe API
  carsDf.select(col("Name")).where(col("Origin")==="USA")

  //spark Sql
  carsDf.createOrReplaceTempView("cars")

  val usCarsDf=spark.sql(
    """
      |select * from cars where Origin='USA'
      |""".stripMargin)
  usCarsDf.show()
}
