package in.olc.scala.recursion

object factorial {
  def main(args:Array[String]):Unit={
    val n=6
    val result=fact(n,1)
    println(s"factorial of $n is : $result")
    val resultsum=sum(n,0)
    println(s"sum of numbers from $n to 1 is : $resultsum")
  }
  def fact(n:Int,result:Int):Int={
    if(n<=1) result
    else n*fact(n-1,1)
  }
  def sum(n:Int,result:Int):Int={
    if(n==0) result
    else n+sum(n-1,result)
  }

}
