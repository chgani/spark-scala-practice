package in.olc.scala.sparkPractice

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkContext

object minMaxTemparature {
    def parser(line:String)={
    val fields=line.split(",")
    val stationID=fields(0)
    val typeOfEntry=fields(2)
    val temperature=fields(3).toFloat*(0.1f)*(9.0f/5.0f)+32.0f
    (stationID,typeOfEntry,temperature)
  }
  def main(args:Array[String])={
    Logger.getLogger("org").setLevel(Level.ERROR)
    val sc=new SparkContext("local[*]","Temparature Example")
    val inRDD=sc.textFile("src/main/scala/in/olc/scala/resources/1800.csv")
    val reqTuple=inRDD.map(x=>parser(x))
    val minTemp=reqTuple.filter(x=>x._2=="TMIN").map(x=>(x._1,x._3))
    val minTemperatureByStation=minTemp.reduceByKey((x,y)=>math.min(x,y))
    val results=minTemperatureByStation.collect()

    for(result<-results){
      val station=result._1
      val temperature=result._2
      println(s"station $station has minimum temperature as $temperature F")
    }
    val maxTemp=reqTuple.filter(x=>x._2=="TMAX").map(x=>(x._1,x._3))
    val maxTempByStation=maxTemp.reduceByKey((x,y)=>math.max(x,y))
    val resultsmax=maxTempByStation.collect()
    for (result <- resultsmax) {
      val station = result._1
      val temperature = result._2
      println(s"station $station has maximum temperature as $temperature F")
    }
  }

}
