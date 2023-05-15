package in.olc.scala.recursion

object printNum {
  def printnto1(n:Int):Unit={
      if(n==0) return
      else{
        println(n)
        printnto1(n-1)
      }
  }

  def print1ton(n: Int): Unit = {
    if (n == 0) return
    else {
      print1ton(n - 1)
      println(n)
    }
  }

  def main(args:Array[String]):Unit={
    println(s"numbers from n to 1")
    printnto1(5)
    println(s"numbers from 1 to n")
    print1ton(5)
  }

}
