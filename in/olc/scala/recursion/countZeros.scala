package in.olc.scala.recursion

object countZeros {
def countZ(n:Int,count:Int):Int= {
  if (n == 0) count
  else {
    val rem = n % 10
    if (rem != 0) countZ(n / 10, count)
    else countZ(n / 10, count + 1)
  }
}
  def main(args:Array[String]):Unit={
    val result=countZ(302090,0)
    println(result)
  }
}
