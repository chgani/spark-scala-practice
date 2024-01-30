package in.olc.scala.sparkPractice

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.{Row, SparkSession}

object interviewQue {

  Logger.getLogger("org").setLevel(Level.ERROR)
def parser(line:String)={
  val fields=line.split("@\\$")
  Row(fields(0).toInt,fields(1),fields(2))
}
  def main(args: Array[String]) = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("interview question")
    val spark = SparkSession.builder().config(conf).getOrCreate()
val rdd1=spark.sparkContext.textFile("C:/Users/ganes/OneDrive/Desktop/New_folder/filedata_multidelim.txt")
    //val rdd_process=rdd1.filter{x=>x.startsWith("empid")}
    val header=rdd1.first()//.toList
    val rdd_process=rdd1.filter(line=>line!=header)
    val p_rdd=rdd_process.map(x=>parser(x))

    val schema = StructType(Seq(
      StructField("empid", IntegerType, nullable = true),
      StructField("empName", StringType, nullable = true),
      StructField("dept", StringType, nullable = true)
    ))
    val df=spark.createDataFrame(p_rdd,schema)

    df.show()
  }
}
