package in.olc.scala.sparkPractice
import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.avg

// find out average salry of emloyees working under each manager
object avgManagerSalary {
  Logger.getLogger("org").setLevel(Level.ERROR)

  def main(args: Array[String]) = {
    val config = new SparkConf().setMaster("local[*]").setAppName("avgManagerSalary")
    val spark = SparkSession.builder().config(config).getOrCreate()
    val data=Seq((10,"Anil",50000, 18),
    (11,"Vikas",75000,16),
    (12,"Nisha",40000,18),
    (13,"Nidhi",60000,17),
    (14,"Priya",80000,18),
    (15,"Mohit",45000,18),
    (16,"Rajesh",90000, 10),
    (17,"Raman",55000, 16),
    (18,"Sam",65000, 17))

    val schema=List("id","name","salary","mgr_id")
    import spark.implicits._
    val df=data.toDF(schema:_*)
 val pr_df=df.as("df1").join(df.alias("df2"),$"df1.id"===$"df2.mgr_id","inner")
   .select($"df2.mgr_id",$"df1.name".as("manager_name"),$"df2.salary")

val pr_df2=pr_df.groupBy("mgr_id","manager_name").agg(avg("salary").as("avg_salary"))

    pr_df2.orderBy("mgr_id").show()


  }
}
