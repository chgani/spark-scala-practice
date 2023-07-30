package in.olc.scala.sparkPractice

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types._

object RJVMDataSources  extends App{

  Logger.getLogger("org").setLevel(Level.ERROR)

  val spark=SparkSession.builder().appName("Data Sources & Formats").config("spark.master","local[*]").getOrCreate()

  val carsSchema = StructType(Array(
    StructField("Name", StringType),
    StructField("Miles_per_Gallon", DoubleType),
    StructField("Cylinders", LongType),
    StructField("Displacement", DoubleType),
    StructField("Horsepower", LongType),
    StructField("Weight_in_lbs", LongType),
    StructField("Acceleration", DoubleType),
    StructField("Year", DateType),
    StructField("Origin", StringType)
  ))

  val carsDF = spark.read
    .format("json")
    .schema(carsSchema)
   .option("dateFormat","yyyy-MM-dd")
    .option("mode","failFast") //dropMalformed,permissive (default)
    .option("path","src/main/resources/data/cars.json")
    // .option("inferSchema", "true")
    .load()
// carsDF.printSchema()
//  carsDF.show()

  val carsDfWithMap=spark.read.format("json")
    .options(Map(
      "mode"->"failFast",
      "inferSchema"->"true",
      "path"->"src/main/resources/data/cars.json"

    )).load()
  //carsDfWithMap.printSchema()

  /*
   Writing DFs
   - format
   - save mode = overwrite, append, ignore, errorIfExists
   - path
   - zero or more options
  */

 // carsDF.write.format("json").mode(SaveMode.Overwrite).option("path","src/main/resources/data/cars_dup.json").save()

/*
  // JSON flags
  spark.read
    .schema(carsSchema)
    .option("dateFormat", "YYYY-MM-dd") // couple with schema; if Spark fails parsing, it will put null
    .option("allowSingleQuotes", "true")
    .option("compression", "uncompressed") // bzip2, gzip, lz4, snappy, deflate
    .json("src/main/resources/data/cars.json")
*/
/*
  // CSV flags
  val stocksSchema = StructType(Array(
    StructField("symbol", StringType),
    StructField("date", DateType),
    StructField("price", DoubleType)
  ))

  spark.read
    .schema(stocksSchema)
    .option("dateFormat", "MMM dd YYYY")
    .option("header", "true")
    .option("sep", ",")
    .option("nullValue", "")
    .csv("src/main/resources/data/stocks.csv")

  // Parquet
  carsDF.write
    .mode(SaveMode.Overwrite)
    .save("src/main/resources/data/cars.parquet")

  // Text files
  spark.read.text("src/main/resources/data/sampleTextFile.txt").show()
*/

  // Reading from remote DB
  val driver = "org.postgresql.Driver"
  val url = "jdbc:postgresql://localhost:5432/rtjvm"
  val user = "docker"
  val password = "docker"
//  val dbtable="employees"
//
//  val employeeDF=spark.read
//    .format("jdbc")
//    .options(Map(
//      "driver"->driver,
//      "url" -> url,
//      "user"->user,
//      "password"->password,
//      "dbtable"->dbtable
//    ))
//    .load()


  val employeesDF = spark.read
    .format("jdbc")
    .option("driver", driver)
    .option("url", url)
    .option("user", user)
    .option("password", password)
    .option("dbtable", "public.employees")
    .load()

  //employeesDF.show()

//  val df_user = spark.read.format("jdbc")
//    .option("url", "jdbc:mysql://localhost:3306/practice")
//    .option("driver", "com.mysql.cj.jdbc.Driver")
//    .option("dbtable", "users")
//    .option("user", "root")
//    .option("password", "bullet")
//    .load()
//  df_user.show()

  val df_movies=spark.read.json("src/main/resources/data/movies.json")
//  df_movies.show()

//  df_movies.write
//    .format("csv")
//    .option("sep","\t")
//    .option("header",true)
//    .mode(SaveMode.Overwrite)
//    .save("src/main/resources/data/movies.csv")

//  df_movies.write
//    .option("compression","snappy")
//    .mode(SaveMode.Overwrite)
//    .parquet("src/main/resources/data/moviesParquet")

  df_movies.write
    .format("jdbc")
    .options(Map(
    "driver"-> driver,
    "url" -> url,
    "user"->user,
    "password"->password,
    "dbtable" ->"public.movies"
  ))
    .save()

}
