package in.olc.scala.scalaPractice

object specialNumber {
  def main(args: Array[String]): Unit = {
    println("Enter a number: ")
    val num = scala.io.StdIn.readInt()
    val list=num.toString.map(_.asDigit).toList.map(x=>factorial(x)).sum
 //   println(list)
if(num==list) println(s"$num is a magic number") else println(s"$num is not a special number")
  }
  def factorial(n: Int): Int = {
    var result: Int = 1
    for (i <- 1 to n) {
      if (i == 1) result
      else result = result * i
    }
    result
  }
}
