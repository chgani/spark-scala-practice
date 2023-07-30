package in.olc.scala.sparkPractice

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{expr,max,col}


object RJVMDataFrameJoins extends App {
  Logger.getLogger("org").setLevel(Level.ERROR)

  val spark=SparkSession.builder().appName("Joins").config("spark.master","local[*]").getOrCreate()

  val guitarsDf=spark.read
    .option("inferSchema",true)
    .json("src/main/resources/data/guitars.json")

  val guitarplayersDf = spark.read
    .option("inferSchema", true)
    .json("src/main/resources/data/guitarplayers.json")

  val bandsDf = spark.read
    .option("inferSchema", true)
    .json("src/main/resources/data/bands.json")

  //supported joins 'inner', 'outer', 'full', 'fullouter', 'full_outer', 'leftouter', 'left',
  // 'left_outer', 'rightouter', 'right', 'right_outer', 'leftsemi', 'left_semi', 'leftanti', 'left_anti', 'cross'.

  //innner joins
  val joinCondition=guitarplayersDf.col("band")===bandsDf.col("id")
  val joinType="inner"
  val bandsOfGuitaristsDf=guitarplayersDf.join(bandsDf,joinCondition,joinType)
  //bandsOfGuitaristsDf.printSchema()
  //bandsOfGuitaristsDf.show()

  //outer joins
  val bansOfPlayersDf1=guitarplayersDf.join(bandsDf,joinCondition,"left") //we can use left_outer also
 // bansOfPlayersDf1.show()

  val bansOfPlayersDf2=guitarplayersDf.join(bandsDf,joinCondition,"right") //we can use right_outer also
//bansOfPlayersDf2.show

  val bansOfPlayersDf3 = guitarplayersDf.join(bandsDf, joinCondition, "full") //we can use outer in place of join type
 // bansOfPlayersDf3.show

  //semi joins =everything in the left DF for which there is a row in the right DF satisfying the condition
  guitarplayersDf.join(bandsDf,joinCondition,"left_semi")//.show()

  //Anti joins =everything in the left DF for which there is NO row in the right DF satisfying the condition
  guitarplayersDf.join(bandsDf,joinCondition,"left_anti")//.show()

//notes
 // bandsOfGuitaristsDf.select("id","name")//.show //will throws ambiguous column error

  // option 1 - rename the column on which we are joining
  guitarplayersDf.join(bandsDf.withColumnRenamed("id","band"),"band")//.show

  // option 2 - drop the dupe column

bandsOfGuitaristsDf.drop(bandsDf.col("id"))//.show

  // option 3 - rename the offending column and keep the data
  val bandsModDF = bandsDf.withColumnRenamed("id", "bandId")
  guitarplayersDf.join(bandsModDF, guitarplayersDf.col("band") === bandsModDF.col("bandId"))

  guitarplayersDf
    .join(guitarsDf.withColumnRenamed("id", "guitarId"), expr("array_contains(guitars, guitarId)"))

  /**
   * Exercises
   *
   * 1. show all employees and their max salary
   * 2. show all employees who were never managers
   * 3. find the job titles of the best paid 10 employees in the company
   */

  val driver = "org.postgresql.Driver"
  val url = "jdbc:postgresql://localhost:5432/rtjvm"
  val user = "docker"
  val password = "docker"

  def readTable(tableName:String)=
    spark.read
      .format("jdbc")
      .option("driver",driver)
      .option("url",url)
       .option("user" ,user)
        .option("password", password)
      .option("dbtable",s"public.$tableName")
      .load()

    val employeeDf=readTable("employees")
    employeeDf.printSchema()
  employeeDf.show(5)
  val managersDf=readTable("dept_manager")
  managersDf.printSchema()
  managersDf.show(5)
  val salaryDf=readTable("salaries")
  salaryDf.printSchema()
  salaryDf.show(5)
  val titlesDf=readTable("titles")
  titlesDf.printSchema()
  titlesDf.show(5)

  // 1
//  val maxSalariesPerEmpNoDF = salaryDf.groupBy("emp_no").agg(max("salary").as("maxSalary"))
//  val employeesSalariesDF = employeeDf.join(maxSalariesPerEmpNoDF, "emp_no")

  val joinCondition1=employeeDf("emp_no")===salaryDf("emp_no")
  val empSalaryDf=employeeDf.join(salaryDf,joinCondition1,"inner").drop(salaryDf("emp_no"))
  //empSalaryDf.show(10)

  val empMaxSalaryDf=empSalaryDf.groupBy("emp_no","first_name","last_name")
    .agg(max("salary").as("maximum_salary")).orderBy(col("maximum_salary").desc)
  //empMaxSalaryDf.show()


  //2 employees who were never managers
  val empNeverManagersDf=employeeDf.join(managersDf,employeeDf("emp_no")===managersDf("emp_no"),"leftanti")
  //empNeverManagersDf.show()

  //3 job titles of the best paid 10 employees in the company

  //we have empSalaryDf

  //val empTitlesDf=empSalaryDf.join(titlesDf,empSalaryDf("emp_no")===titlesDf("emp_no"),"inner").drop(titlesDf.col("emp_no"))

  val mostRecentJobTitlesDf=titlesDf.groupBy("emp_no","title").agg(max("to_date"))
val bestPaidEmployeesDf=empMaxSalaryDf.limit(10)
 // bestPaidEmployeesDf.show()
val bestPaidJobs=bestPaidEmployeesDf.join(mostRecentJobTitlesDf,
  bestPaidEmployeesDf("emp_no")===mostRecentJobTitlesDf("emp_no"))

  bestPaidJobs.show()
}
