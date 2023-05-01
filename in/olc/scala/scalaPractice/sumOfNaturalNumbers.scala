package in.olc.scala.scalaPractice

object sumOfNaturalNumbers {
  def main(args:Array[String]):Unit={
    println("Enter a number: ")
    val num = scala.io.StdIn.readInt()
    val sum=sumOfNatural(num,1)
    println(s"the sum of natural numbers from 1 to $num is=$sum")
  }
  def sumOfNatural(n:Int,result:Int)={
    if(n==0) println(s"Entered number is $n,zero is not a natural number")
    else if (n==1) result
    else {
      val x= (1 to n).toList
      x.sum
  }
}
}
