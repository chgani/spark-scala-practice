package in.olc.scala.scalaPractice

object factorial {
  def main(args:Array[String]):Unit={
    println("Enter a number: ")
    val num = scala.io.StdIn.readInt()
    val fact=factorial(num)
    println(s"the factorial of $num is =$fact")
  }
def factorial(n:Int):Int={
  var result:Int=1
  for (i<-1 to n){
    if(n==1)  return result
    else result=result*i
  }
  result
  }
}
