package in.olc.scala.recursion

class nthFibonacci {
def fibonacci(n:Int):Int={
if(n<2) {
  return n
}
 fibonacci(n-1)+fibonacci(n-2)
}
}
object fib{
  def main(args:Array[String]):Unit={
    val fibo=new nthFibonacci
    val n:Int=15
    val result=fibo.fibonacci(n)
    println(s"the $n th number in fibonacci series is: $result")
  }
}
