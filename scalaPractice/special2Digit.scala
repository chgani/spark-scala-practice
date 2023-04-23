package scalaPractice

object special2Digit {
  def main(args:Array[String]):Unit={
    println("Enter a number: ")
    val num = scala.io.StdIn.readInt()
    val x=sumOfDigits(num)+productOfDigits(num)
    if(num==x) println(s"$num is a special two digit number") else println(s"$num is not a special two digit number")
  }
  def sumOfDigits(n:Int):Int={
    n.toString.map(_.asDigit).toList.reduce(_+_)
  }
  def productOfDigits(n:Int):Int={
    n.toString.map(_.asDigit).toList.reduce(_*_)
  }
}
