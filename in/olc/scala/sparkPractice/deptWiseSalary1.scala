package in.olc.scala.sparkPractice

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object deptWiseSalary1 {

  def main(args: Array[String]) = {
    Logger.getLogger("org").setLevel(Level.ERROR)
    val conf = new SparkConf().setAppName("Salary breakup1").setMaster("local[*]")
    val spark = SparkSession.builder().config(conf).getOrCreate()

    val random = new scala.util.Random
    val empNames = List("Marlania", "Genece", "Kimberli", "Lakken", "Micaila", "Tarvares", "Briana", "Amalia", "Gabriella",
      "Latoynia", "Medusa", "Dondrick", "Thurley", "Afton", "Maysha", "Shaquria", "Gion", "Jamacia", "Kubra", "Kaelea")
    val data = (0 until 20).map { i =>
      (s"${empNames(i)}", random.nextInt(5) + 1, random.nextInt(50000) + 50000)
    }
    val schema=List("empName","dept","salary")
    import spark.implicits._
    val df1=data.toDF(schema:_*)
    df1.show()

}

}
