package in.olc.scala.scalaPractice

import java.time.LocalDateTime
import scala.collection.mutable.ListBuffer


object leapYear {
  def main(args:Array[String]):Unit={
    println("Enter the year of birth")
    val year=scala.io.StdIn.readInt()
    val currentyear=LocalDateTime.now().getYear
    val listOfYears=(year to currentyear by 1).toList
    //println(listOfYears)
    var leapyears=ListBuffer[Int]()
    for(i<-listOfYears){
      if(i%4==0){
        leapyears+=i
      }
      else leapyears
    }
    leapyears.toList
    println(leapyears)
    val numberOfLeapYears=leapyears.size
    println(s"there are $numberOfLeapYears leap years between $year and $currentyear")
  }

}
