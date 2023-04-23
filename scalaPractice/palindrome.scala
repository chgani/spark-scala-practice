package scalaPractice

object palindrome {
  def main(args:Array[String]):Unit={
    println("Enter a number: ")
    val num=scala.io.StdIn.readInt()
    if(num==reverseOfNum(num)) println(s"$num is a palindrome") else println(s"$num is not a palindrome")
  }
  def reverseOfNum(number:Int):Int={
    number.toString.reverse.toInt
  }

}
