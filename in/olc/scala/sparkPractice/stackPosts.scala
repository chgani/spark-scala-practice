package in.olc.scala.sparkPractice

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
//case class Post(
//                commentCount:Option[Int],
//                lastActivityDate:Option[java.sql.Timestamp],
//                ownerUserId:Option[Long],
//                body:String,
//                score:Option[Int],
//                creationDate:Option[java.sql.Timestamp],
//                viewCount:Option[Int],
//                title:String,
//                tags:String,
//                answerCount:Option[Int],
//                acceptedAnswerId:Option[Long],
//                postTypeId:Option[Long],
//                id:Long)
object stackPosts {
  Logger.getLogger("org").setLevel(Level.ERROR)
    def main(args:Array[String])={
      val sparkconf=new SparkConf().setAppName("stack overflow posts").setMaster("local[*]")
      val spark=SparkSession.builder().config(sparkconf).getOrCreate()
      val rawposts_rdd=spark.sparkContext.textFile("C:\\Users\\ganes\\Downloads\\datasets\\italianPosts.csv")
      val raw_rdd_split=rawposts_rdd.map(x => x.split("~")).map(x=>
          (x(0),x(1),x(2),x(3),x(4),x(5),x(6),x(7),x(8),x(9),x(10),x(11),x(12)))
import spark.implicits._
      val itPostsDF = raw_rdd_split.toDF("commentCount", "lastActivityDate",
        "ownerUserId", "body", "score", "creationDate", "viewCount", "title",
        "tags", "answerCount", "acceptedAnswerId", "postTypeId", "id")
      itPostsDF.show()
      }



}
