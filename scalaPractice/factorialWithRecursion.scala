package scalaPractice

object factorialWithRecursion {
  def main(args:Array[String]):Unit={
    println("Enter a number: ")
    val num = scala.io.StdIn.readInt()
    val fact=factorialR(num,1)
    println(s"The factorial of $num is =$fact")
  }
  def factorialR(n:Int,result:Int):Int={
    if(n==1) result
    else n*factorialR(n-1,1)
  }

}
