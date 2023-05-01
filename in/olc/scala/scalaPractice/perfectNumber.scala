package in.olc.scala.scalaPractice

import scala.collection.mutable.ListBuffer

object perfectNumber {
  def main(args: Array[String]): Unit = {
    println("Enter a number: ")
    val num = scala.io.StdIn.readInt()
    var lop=ListBuffer[Int]()
    for (i <- 1 to num-1) {
      if (num % i == 0)
 lop.append(i)
    }
    lop.toList
    val result=lop.sum
    if(num==result) println(s"$num is a perfect number") else println(s"$num is not a perfect number")
  }
}
