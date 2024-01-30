package in.olc.scala.sparkPractice

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types.{StringType, StructField, StructType}

object unStructuredData2 {
    Logger.getLogger("org").setLevel(Level.ERROR)

    def main(args: Array[String]) = {
      val conf = new SparkConf().setAppName("unstructured data").setMaster("local[*]")
      val spark = SparkSession.builder().config(conf).getOrCreate()
      val in = spark.sparkContext.textFile("src/main/resources/data/unstructured.txt")

      val length=in.first().length
      //suppose if we want 10 characters in each column
      val columns=(length/10) //+ (if(length%10==0 ) 0 else 1)

      val schema=new StructType((1 to columns).map(x=>StructField(s"column_$x",StringType,true)).toArray)

      val processed_rdd=in.map(x=>x.grouped(10).toSeq).map(data=>Row(data:_*))

      val df=spark.createDataFrame(processed_rdd,schema)
      df.show()

    }

}
