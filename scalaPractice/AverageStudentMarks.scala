package scalaPractice

object AverageStudentMarks {
  def main(args:Array[String]):Unit={
    println("Enter theory marks:")
    val theory=scala.io.StdIn.readFloat()
    println("Enter practical marks:")
    val practical = scala.io.StdIn.readFloat()
    val avg=(theory+practical)/2
    println(s"the average marks =$avg")
    if(avg>=90) println("very good") else println("work hard")
  }

}
