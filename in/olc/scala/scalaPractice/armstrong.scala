package in.olc.scala.scalaPractice

object armstrong {
  def main(args:Array[String]):Unit= {
    println("Enter a number: ")
    val num = scala.io.StdIn.readInt()
    if(num==sumOfCubesOfEachDigit(num)) println(s"$num is an armstrong number") else println(s"$num is not an armstrong number")

  }
  def sumOfCubesOfEachDigit(number:Int):Int ={
    val s=number.toString.map(_.asDigit).toList.map(x=>x*x*x).sum
    s
  }
}
