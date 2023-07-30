package in.olc.scala.sparkPractice

import org.apache.log4j._
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object aggregationsDF {
  def main(args:Array[String]):Unit= {
    Logger.getLogger("org").setLevel(Level.ERROR)
    val spark = SparkSession.builder().appName("aggregations").master("local[*]").getOrCreate()
    import spark.implicits._
//    val data = Seq(
//      ("Alice", 25, 100),
//      ("Bob", 30, 150),
//      ("Alice", 35, 200),
//      ("Bob", 40, 300),
//      ("Charlie", 45, 250)
//    ).toDF("name", "age", "salary");
//
//    val df = data.select(max("salary").as("maxSalary"), min("salary").as("minSalary"),
//      avg("salary").as("avgSalary"), sum("salary").as("totalSalary"))
//    df.show()


  //  Finding the count of orders placed by each customer and the total order amount  for each customer
//   val orderData = Seq(
//      ("Order1", "John", 100),
//      ("Order2", "Alice", 200),
//      ("Order3", "Bob", 150),
//      ("Order4", "Alice", 300),
//      ("Order5", "Bob", 250),
//      ("Order6", "John", 400),
//     ("Order7","Alice",600)
//    ).toDF("OrderID", "Customer", "Amount")
//    println("Customer wise orders")
//    val df=orderData.groupBy("Customer").agg(count("OrderID").alias("CustomerWiseOrders"))
//    df.show()
//    println("Customer wise total order amount")
//val df2=orderData.groupBy("Customer").agg(sum("Amount").alias("CustomerWiseAmount"))
//    df2.show()

    //Average salary by department

//    val data = Seq(
//      ("Alice", "Sales", 5000),
//      ("Bob", "Engineering", 6000),
//      ("Charlie", "Sales", 4500),
//      ("Dave", "Engineering", 7000),
//      ("Eve", "HR", 4000)
//    ).toDF("Name", "Department", "Salary")
//
//    val df=data.groupBy("Department").agg(avg("salary").alias("DepartmentWiseAvgSalary")).sort("DepartmentWiseAvgSalary")
//    df.show()

   // Finding the average score for each subject and the maximum score for each student.

//    val scoreData = Seq(
//      ("Alice", "Math", 80),
//      ("Bob", "Math", 90),
//      ("Alice", "Science", 70),
//      ("Bob", "Science", 85),
//      ("Alice", "English", 75),
//      ("Bob", "English", 95)
//    ).toDF("Student", "Subject", "Score")
//
//    val df=scoreData.groupBy("Subject").agg(avg("Score").as("SubjectWiseAvgScore"))
//    df.show()
//    val df1=scoreData.groupBy("Student").agg(max("Score").as("StudentWiseMaxScore"))
//    df1.show()


    //Finding the average rating for each movie and the total number of ratings for each movie

//    val ratingsData = Seq(
//      ("User1", "Movie1", 4.5),
//      ("User2", "Movie1", 3.5),
//      ("User3", "Movie2", 2.5),
//      ("User4", "Movie2", 3.0),
//      ("User1", "Movie3", 5.0),
//      ("User2", "Movie3", 4.0)
//    ).toDF("User", "Movie", "Rating")
//
//    val df_avgAndTotalRating=ratingsData.groupBy("Movie").agg(avg("Rating").as("MovieWiseAvgRating"),
//      count("Rating").as("MovieWiseTotalRatings"))
//    df_avgAndTotalRating.show()


    // Finding the count of occurrences for each word in a text document
//    val textData = Seq(
//      "Hello, how are you?",
//      "I am fine, thank you!",
//      "How about you?"
//    ).toDF("Text")
//    textData.show(false)
//    // Split the text into words
//    val df_words = textData.select(explode(split($"Text", "\\s+")).alias("Word"))
//    val df_wordCount=df_words.groupBy("word").agg(count("word").as("wordCount"))
//    df_wordCount.show()

    //Finding the minimum, maximum, and average temperature for each city in a weather dataset

//    val weatherData = Seq(
//      ("City1", "2022-01-01", 10.0),
//      ("City1", "2022-01-02", 8.5),
//      ("City1", "2022-01-03", 12.3),
//      ("City2", "2022-01-01", 15.2),
//      ("City2", "2022-01-02", 14.1),
//      ("City2", "2022-01-03", 16.8)
//    ).toDF("City", "Date", "Temperature")
//
//    val df_weather=weatherData.withColumn("Date",to_date(col("Date"),"yyyy-MM-dd"))
//
//    val df_Agg=df_weather.groupBy("City").agg(max("Temperature").as("maxTemperature"),
//      min("Temperature").as("minTemperature"),avg("Temperature").as("avgTemperature"))
//    df_Agg.show()

    //Finding the count of distinct products purchased by each customer and the total purchase
    //amount for each customer.

    val purchaseData = Seq(
      ("Customer1", "Product1", 100),
      ("Customer1", "Product2", 150),
      ("Customer1", "Product3", 200),
      ("Customer2", "Product2", 120),
      ("Customer2", "Product3", 180),
      ("Customer3", "Product1", 80),
      ("Customer3", "Product3", 250)).toDF("Customer", "Product", "Amount")
    

    val data=Seq(("A",10),("B",15),("A",20),("B",25),("A",30)).toDF("Group","Value")
    val df_agg=data.groupBy("Group").agg(sum("Value").cast("bigint").as("Total"),
      when(sum("Value") >25,"High").otherwise("Low").as("Category"))
    df_agg.show()
  }
}
