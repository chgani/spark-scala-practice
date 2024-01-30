package in.olc.scala.sparkPractice

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object mapPartitionIndex {

  Logger.getLogger("org").setLevel(Level.ERROR)

  def main(args: Array[String]) = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("map partition")
    val spark = SparkSession.builder().config(conf).getOrCreate()
    val carsDf = spark.read
      .option("inferSchema", true)
      .json("src/main/resources/data/cars.json").repartition(4)

    println(carsDf.rdd.getNumPartitions)

      // print first partition
//    val partitions=carsDf.rdd.getNumPartitions
//
//    if (partitions>0) {
//      val firstPartitionData=carsDf.rdd.glom().first()(0)
//      firstPartitionData
//    }









//    val first_partition=carsDf.rdd.mapPartitionsWithIndex((index:Int,iter:Iterator[Row])=>{
//      iter.map(x=>if(index==0) {
//        println(x)
//      })
//    })
    //first_partition.foreach(println)


//    import spark.implicits._
//   val result=carsDf.rdd.mapPartitionsWithIndex((index,iterator)=>{
//      iterator.map(record=>(index,record))
//    }).toDF("partition","col1","col2","col3","col4","col5","col6","col7","col8","col9","col10")
//result.show()
//   result.foreach(println)

//    val result_df=carsDf.select(spark_partition_id().as("partition_number"),col("*")).where(col("partition_number")===3)
//    result_df.show()
  }
}
