package in.olc.scala.sparkPractice

//find the city/cities in which the covid cases are increasing day by day
import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{col, countDistinct, rank, to_date}

object CovidCasesWindowFunc {
  def main(args: Array[String]) = {
    Logger.getLogger("org").setLevel(Level.ERROR)
    val conf = new SparkConf().setAppName("Window functions").setMaster("local[*]")
    val spark = SparkSession.builder().config(conf).getOrCreate()

    val data= Seq(("DELHI", "2022-01-01", 100),("DELHI", "2022-01-02", 200),("DELHI", "2022-01-03", 300),
      ("MUMBAI", "2022-01-01", 100),("MUMBAI", "2022-01-02", 100),("MUMBAI", "2022-01-03", 300),
      ("CHENNAI", "2022-01-01", 100),("CHENNAI", "2022-01-02", 200),("CHENNAI", "2022-01-03", 150),
      ("BANGALORE", "2022-01-01", 100),("BANGALORE", "2022-01-02", 300),("BANGALORE", "2022-01-03", 200),
      ("BANGALORE", "2022-01-04", 400))

    val schema=List("city","date","cases")
    import spark.implicits._
    val in_df1=data.toDF(schema:_*)
    val df2=in_df1.withColumn("date",to_date(col("date"),"yyyy-MM-dd"))
    println(df2.rdd.getNumPartitions)
    println(df2.rdd.toDebugString)
    val windowFuncDate=Window.partitionBy(col("city")).orderBy(col("date").asc)
    val windowFuncCases=Window.partitionBy(col("city")).orderBy(col("cases").asc)

    val df3=df2.withColumn("date_rank",rank().over(windowFuncDate))
      .withColumn("cases_rank",rank().over(windowFuncCases))

    val df4=df3.withColumn("flag",col("date_rank")-col("cases_rank"))

    val df5=df4.groupBy("city").agg(countDistinct("flag").as("result"))
      .where(col("result")===1).select("city")
    df5.show()
  }
}
