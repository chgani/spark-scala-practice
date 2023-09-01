package in.olc.scala.sparkPractice

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object filterPrac {
  def main(args:Array[String]):Unit={
    Logger.getLogger("org").setLevel(Level.ERROR)
    val spark = SparkSession.builder.appName("filter").master("local[*]").getOrCreate()
    import spark.implicits._
   // val df = Seq(("Alice", 25), ("Bob", 30), ("Charlie", 35)).toDF("name","age")
//    val t_df=df.select(col("name"),col("age"),when(col("age")<30,"young").otherwise("old").as("category"))
//t_df.show()
//    val df2=df.filter(col("age")>25 && col("name").startsWith("C"))
//    df2.show()

//    val t_df3 = Seq(("Alice", 25), ("Bob", null), ("Charlie", 35)).toDF("name", "age")
//    val t_df4=t_df3.filter(col("age").isNull)
//    t_df3.show()

//    val schema = StructType(Seq(StructField("name", StringType), StructField("age", IntegerType)))
//    val data = Seq(("Alice", 25), ("Bob", null), (null, 35))
//    val rows = data.map { case (name, age) => Row(name, age) }
//    val df = spark.createDataFrame(spark.sparkContext.parallelize(rows), schema)
//    val t_df=df.filter(col("age").isNotNull)
//    t_df.show()

//    val df=Seq(("Alice","Engineer"),("Bob","manager"),("Charlie","developer")).toDF("name","role")
//    val t_df=df.filter(col("name").contains("li"))
//    t_df.show()

//    val df = Seq(("Alice", 25, 1500), ("Bob", 30, 2000), ("Charlie", 35, 3000),("Ram",31,1000)).toDF("name", "age","salary")
//    val t_df=df.filter(col("salary")>1500 || col("age")>30)
//    t_df.show()

//    val todayDate=LocalDate.now()
//    println("today's date",todayDate)
//    val yesterdayDate=todayDate.minusDays(1)
//    println("yesterdaysDate",yesterdayDate)
//      val futureDate=todayDate.plusDays(30)
//    println("date after 30 days",futureDate)
//
//    val dateFormat=todayDate.format(DateTimeFormatter.ofPattern("dd-MM-YYYY"))
//    println(dateFormat)
    val df=spark.read.format("csv").option("header",true).option("inferSchema",true).load("C:/edu/practice/student.csv")
   // df.printSchema()
   // df.show()
//    val df1=df.withColumn("total",col ("subject1")+col("subject2")+col("subject3"))
//  df1.show()

    val df2=df.selectExpr("student","subject1","subject2","subject3","(subject1+subject2+subject3) as total")
    //df2.show()
    val df3=df2.select(col("*"),when(col("total")<=35, "Fail")
      .when(col("total")>35 and col("total")<=50,"Third")
      .when(col("total")>50 and col("total")<=60,"Second")
      .otherwise("First").as("grade"))
  //  df3.write.save("C:/edu/practice/1207")

//    val df4=spark.read.load("C:/edu/practice/1207/test.parquet")
//    df4.show()
//    df4.printSchema()

  }

}
