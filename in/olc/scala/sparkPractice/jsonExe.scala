package in.olc.scala.sparkPractice

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}

// printout the short names of the states that start with a vowel:
object jsonExe {
def main(args:Array[String]):Unit={
  Logger.getLogger("org").setLevel(Level.ERROR)
  val conf=new SparkConf().setAppName("Json Exercise").setMaster("local[*]")
  val spark=SparkSession.builder().config(conf).getOrCreate()
  val jsonData="""[{"country": "India","states": [{"name": "Andhra Pradesh","capital": "Amaravathi","official_language": "Telugu","other_names": [{"name": "AP","type": "SHORT_NAME"}]},{"name": "Telangana","capital": "Hyderabad","official_language": "Telugu","other_names": [{"name": "TN","type": "SHORT_NAME"}]}]}]"""
//val jsonData=URLDecoder.decode(encodedJson,"UTF-8")
  import spark.implicits._
val j_ds=spark.createDataset(Seq(jsonData))
  val df1:DataFrame=spark.read.option("multiline",true).json(j_ds)
  //val df1=j_df.withColumn("states",explode(col("country.states")))
val country=df1.select("country")
//val s_df=df1.select(explode($"states"))
}
}
