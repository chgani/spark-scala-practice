package in.olc.scala.sparkPractice

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}

object corruptedRecords {

  Logger.getLogger("org").setLevel(Level.ERROR)

  def main(args: Array[String]) = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("corruptes records")
    val spark = SparkSession.builder().config(conf).getOrCreate()
//    // Create a DataFrame with the given data and schema
//    val data = Seq(
//      (1, "Manish", 26, 75000, "bihar", "nominee1"),
//      (2, "Nikita", 23, 100000, "uttarpradesh", "nominee2"),
//      (3, "Pritam", 22, 150000, "Bangalore", "nominee3"),
//      (4, "Prantosh", 17, 200000, "Kolkata", "nominee4"),
//      (5, "Vikash", 31, 300000,"","nominee5")
//    )
//    val schema=List("id","name","age","salary","address","nominee")
//    import spark.implicits._
//    val df=spark.sparkContext.parallelize(data).toDF(schema:_*)
//    df.show()
val schema=StructType(Seq(
  StructField("id",IntegerType,true),
  StructField("name",StringType,true),
  StructField("age",IntegerType,true),
  StructField("salary",IntegerType,true),
  StructField("address",StringType,true),
  StructField("nominee",StringType,true),
  StructField("_corrupted_record",StringType,true)
))
    val df=spark.read.format("csv").option("mode","PERMISSIVE") //PERMISSIVE(DEFAULT),DROPMALFORMED,FAILFAST
      .option("header",true).option("inferSchema",true).schema(schema)
      .load("C:/Users/ganes/OneDrive/Desktop/New_folder/person.csv")

    df.show()

  }
}
