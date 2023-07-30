package in.olc.scala.sparkPractice

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.spark_partition_id

object dfJoins {
  def main(args:Array[String]):Unit={
    Logger.getLogger("org").setLevel(Level.ERROR)
    val spark=SparkSession.builder().appName("Joins").master("local[*]").getOrCreate()
    val employee = Seq((1, "ramu", 3, "2018", 10001, "M", 25000),
      (2, "raju", 1, "2010", 20001, "M", 40000),
      (3, "mahesh", 1, "2010", 10001, "M", 35000),
      (4, "suresh", 2, "2005", 10001, "M", 45000),
      (5, "likitha", 2, "2010", 40001, "F", 35000),
      (6, "lavanya", 2, "2010", 50001, "F", 40000),
      (8, "madhu", 1, "2011", 50001, "", 40000))
    val emp_schema=Seq("emp_id","name","reporting_head_id","year_joined","dept_id","gender","salary")
    import spark.implicits._
    val emp_df=employee.toDF(emp_schema:_*)
    //emp_df.show()
    val dept = Seq(("Accounts", 10001),
      ("Marketing", 20001),
      ("Finance", 30001),
      ("Engineering", 40001))
    val dept_schema = Seq("department", "dept_id")
    val dept_df = dept.toDF(dept_schema: _*)
    val joinCond=emp_df.col("dept_id")===dept_df.col("dept_id")
   // val joinCond=emp_df("dept_id")===dept_df("dept_id")
    val joinType="inner"
    val join_df=emp_df.join(dept_df,joinCond,joinType)
    join_df.explain()
    //join_df.select("dept_id").show()
    println("Inner Join with handling duplicate column name issue")
    val inner_df = emp_df.join(dept_df, Seq("dept_id"), "inner")
    println(s"********************\npartitions = ${inner_df.rdd.getNumPartitions}\n*******************")
    inner_df.select(spark_partition_id().alias("partition_id")).groupBy("partition_id").count().show()
    inner_df.show()
    inner_df.explain()
  }

}
