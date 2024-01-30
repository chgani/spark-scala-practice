package in.olc.scala.sparkPractice

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions._

object deptWiseSalary {
  def main(args:Array[String])={
    Logger.getLogger("org").setLevel(Level.ERROR)
    val conf=new SparkConf().setAppName("Salary breakup").setMaster("local[*]")
    val spark=SparkSession.builder().config(conf).getOrCreate()
    val data= Seq(("Genece", 2, 75000), ("Jaimin", 2, 80000), ("Pankaj", 2, 80000), ("Tarvares", 2, 70000),
      ("Marlania", 4, 70000), ("Briana", 4, 85000), ("Kimberli", 4, 55000), ("Gabriella", 4, 55000),
      ("Lakken", 5, 60000), ("Latoynia", 5, 65000))
    val schema=List("name","deptid","salary")
    import spark.implicits._
    val in_df=data.toDF(schema:_*)
    val agg_df=in_df.groupBy("deptid").agg(max(col("salary")).alias("max_salary"),min(col("salary")).alias("min_salary"))
    val join_cond=in_df("deptid")===agg_df("deptid")
    val joined_df=in_df.join(agg_df,join_cond,"inner").drop(agg_df("deptid"))
    val salary_flag_df=joined_df.withColumn("salary_flag",
      when(col("salary")===col("max_salary"),1).when(col("salary")===col("min_salary"),2)
        .otherwise(0))

    val salary_flag_df1=joined_df.filter(col("salary")===col("max_salary")||col("salary")===col("min_salary"))
    //salary_flag_df1.show()

//    val final_df=salary_flag_df1.where(col("salary_flag")===1||col("salary_flag")===2).groupBy("deptid","salary")
//      .agg(collect_list(col("name")))
//    final_df.show(false)

    val final_df1 = salary_flag_df1.groupBy("deptid","salary").agg(collect_list(col("name")))
       // final_df1.show(false)

    val df2=in_df.select(col("name"),col("deptid"),col("salary").cast("int"))
//df2.show()
    val win1=Window.partitionBy("deptid").orderBy(col("salary").desc)

    val df3=df2.select(col("*"),dense_rank().over(win1).as("row_num"))
val df4=df3.select("name").filter(col("row_num")===1)
    df3.show()
    df4.show()
  }

}
