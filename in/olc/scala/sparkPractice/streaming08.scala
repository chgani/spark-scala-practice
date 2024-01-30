package in.olc.scala.sparkPractice

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkContext
import org.apache.spark.streaming.Durations.seconds
import org.apache.spark.streaming.StreamingContext

object streaming08 {
  Logger.getLogger("org").setLevel(Level.ERROR)

  def main(args: Array[String]) = {
//    val conf = new SparkConf().setMaster("local[*]").setAppName("streaming")
//    val spark = SparkSession.builder().config(conf).getOrCreate()
//    val in_df=spark.readStream.format("socket").option("host","localhost").option("port",9999).load()
//    val wordsDf=in_df.selectExpr("explode(split(value,' ')) as word")
    val sc= new SparkContext("local[*]","wordcount")
    val ssc=new StreamingContext(sc,seconds(5))
    val lines=ssc.socketTextStream("localhost",9999)
    val word_count=lines.flatMap(x=>x.split(" ")).map(x=>(x,1)).reduceByKey((x,y)=>x+y)
    word_count.print()
    ssc.start()
    ssc.awaitTermination()
  }

}
