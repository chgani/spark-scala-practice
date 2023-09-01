package in.olc.scala.sparkPractice

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.col

object SCD {
  Logger.getLogger("org").setLevel(Level.ERROR)
  def main(args:Array[String])={
    val conf=new SparkConf().setAppName("SCD").setMaster("local[*]")
    val spark=SparkSession.builder().config(conf).getOrCreate()
    import spark.implicits._
    val df1=Seq((1,"Alice",2000),(2,"Bob",3500),(3,"Charlie",2500),(4,"Dany",2200),(5,"Sam",2500))
      .toDF("id","name","salary")
    df1.show()
    val df2=Seq((1,"ALice",3000),(4,"Dany",2300),(6,"Han",3000)).toDF("id","name","salary")
    df2.show()
    val df_update=df1.join(df2,df1("id")===df2("id"),"inner").select(df1("id"),df1("name"),df2("salary"))
    //df_update.show()

    val df_notUpdated=df1.join(df2,df1("id")===df2("id"),"left_anti")
   // df_notUpdated.show()
    val df_newRecords=df1.join(df2,df1("id")===df2("id"),"right").filter(df1("id").isNull)
      .select(df2("id"),df2("name"),df2("salary"))

    val df_final=df_update.union(df_notUpdated).union(df_newRecords).sort(col("id"))
    df_final.show()
  }

}
