package in.olc.scala.sparkPractice

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.apache.log4j.{Level, Logger}
object columnWiseNullCount {
  Logger.getLogger("org").setLevel(Level.ERROR)
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("Scala DataFrame Example")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._

    // Sample DataFrame
    val data = Seq(
      (1, "Alice", 28),
      (2, "Bob", 35),
      (3, null, 40),
      (4, "David", null)
    )
    val df = data.toDF("id", "name", "age")

    // Count null values in each column
    // Count null values in each column
    //val nullCounts = df.selectExpr(df.columns.map(colName => sum(col(colName).isNull.cast("int")).alias(colName)): _*)
    val nullCounts = df.selectExpr(df.columns.map(colName => s"sum(${col(colName).isNull.cast("int")}) as $colName"): _*)

    nullCounts.show()

    nullCounts.show()

    spark.stop()
  }
}
