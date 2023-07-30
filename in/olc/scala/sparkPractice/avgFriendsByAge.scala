package in.olc.scala.sparkPractice

import org.apache.log4j.{Level, Logger}
import org.apache.spark._

object avgFriendsByAge {
   def parser(lines:String)={
    val line=lines.split(",")
    val age=line(2).toInt
    val friends=line(3).toInt
    (age,friends)
  }
  def main(args:Array[String])= {
    Logger.getLogger("org").setLevel(Level.ERROR)
    val sc = new SparkContext("local[*]", "Friends by age")
    val in = sc.textFile("src/main/scala/in/olc/scala/resources/friends-nh.csv")

    val ageFriendsTuple = in.map(x => parser(x))
    val totalByAge = ageFriendsTuple.map(x => (x._1, (x._2, 1))).reduceByKey((x, y) => (x._1 + y._1, x._2 + y._2))
    val avgFriendsByAge = totalByAge.map(x => (x._1, x._2._1 / x._2._2)).sortBy(x => x._1)
    val partitions = avgFriendsByAge.getNumPartitions
    println(s"partitions=$partitions")

    // Function to get the number of records in each partition
    def getPartitionSize(iter: Iterator[(Int, Int)]): Iterator[Int] = {
      Iterator(iter.length)
    }

    // Get the number of records in each partition using mapPartitions
    val partitionSizes = avgFriendsByAge.mapPartitions(getPartitionSize).collect()
    // Print the number of records in each partition
    partitionSizes.zipWithIndex.foreach { case (size, partitionIndex) =>
      println(s"Partition $partitionIndex size: $size")
      val result = avgFriendsByAge.collect()
      result.foreach(println)
    }
  }
}
