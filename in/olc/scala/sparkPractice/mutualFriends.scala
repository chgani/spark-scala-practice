package in.olc.scala.sparkPractice

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, udf}

object mutualFriends {
  Logger.getLogger("org").setLevel(Level.ERROR)
  def main(args:Array[String])={
    val conf=new SparkConf().setMaster("local[*]").setAppName("mutual friends")
    val spark=SparkSession.builder().config(conf).getOrCreate()
    // Create a DataFrame with the given data and schema
    val data = Seq(
      (1, Seq(2, 3, 4)),
      (2, Seq(1, 3, 4)),
      (3, Seq(1, 2)),
      (4, Seq(1, 2))
    )
    val schema = List("user_id", "friend_list")
    val df = spark.createDataFrame(data).toDF(schema: _*)
df.show()
    // Cross-join the DataFrame with itself to create pairs of users
    val pairsDf = df.as("a").crossJoin(df.as("b"))

    // Filter pairs to ensure they are not the same user
    val filteredPairsDf = pairsDf.filter(col("a.user_id") =!= col("b.user_id"))

    // Define a UDF to find the intersection of two arrays
    val arrayIntersectionUDF = udf((array1: Seq[Int], array2: Seq[Int]) => array1.intersect(array2))


    //    // Calculate mutual friends for each pair of users
//    val mutualFriendsDf = filteredPairsDf
//      .withColumn("mutual_friends", array_intersect(col("a.friend_list"), col("b.friend_list")))

    val mutualFriendsDf = filteredPairsDf
      .withColumn("mutual_friends", arrayIntersectionUDF(col("a.friend_list"), col("b.friend_list")))

    // Select the relevant columns and drop duplicates
    val resultDf = mutualFriendsDf
      .select(col("a.user_id"), col("b.user_id"), col("mutual_friends"))
      .orderBy(col("a.user_id").asc)
      .distinct()

    // Show the result
    resultDf.show()

  }

}
