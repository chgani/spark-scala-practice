package in.olc.scala.scalaPractice

object fibnonacciRecursion {
  def main(args:Array[String]):Unit= {
    val n = 8
    for (i <- 0 to n) {
      print(fib(i)+" ")
    }
    println("\n")
    println(fibList(8))
    println(fibList(8).indexOf(8))
  }
    def fib(n: Int):Int = {
      if (n == 0) 0
      else if (n == 1 || n == 2) 1
      else fib(n - 1) + fib(n - 2)

    }
  def fibList(n:Int):List[Int]={
    (0 to n).map(fib).toList
  }


}
