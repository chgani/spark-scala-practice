package in.olc.scala.sparkPractice
//Given a file with positive numbers. Group the numbers such that first group
//has all numbers b/w 1 to 100, second group has all numbers b/w 101 to 200 and soon.Find the group which has
//the maximum number of entries, which group has the maximum sum

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

case class Num(numbers:Int)
object groupNumbers {
  def main(args: Array[String]): Unit = {
    // val schema=new StructType(Array(StructField("Numbers",IntegerType,true)))
    Logger.getLogger("org").setLevel(Level.ERROR)
    val conf = new SparkConf().setAppName("groupNumbers").setMaster("local[*]")
    val spark = SparkSession.builder().config(conf).getOrCreate()
    val in_rdd = spark.sparkContext.textFile("C:/edu/olc/randomnum.txt")
    val rdd1 = in_rdd.map(x => Num(x.toInt))
    //rdd1.foreach(println)
    import spark.implicits._
    val df1 = rdd1.toDF("Number")
    df1.printSchema()
    val fibonacciUDF = udf((n: Int) => isFib(n))
    val positionUDF=udf((n:Int)=>positionFib(n))
    val re_df=df1.withColumn("number_replace",
      when(fibonacciUDF(col("Number")),positionUDF(col("Number"))).otherwise("not Fibonacci"))
    re_df.show()

  }
def isFib(n:Int):Boolean={
  var a=0
  var b=1
  var fib=a+b
  while(fib < n){
    a=b
    b=fib
    fib=a+b
  }
  fib==n
 }

  def positionFib(n:Int):Int={
    var a = 0
    var b = 1
    var fib = a + b
    var pos=2
    while (fib < n) {
      a = b
      b = fib
      fib = a + b
      pos=pos+1
    }
    pos
  }
}