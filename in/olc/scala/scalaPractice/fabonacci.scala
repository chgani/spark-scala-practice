package in.olc.scala.scalaPractice

object fabonacci {
  def main(args: Array[String]): Unit = {
    println("Enter a number: ")
    val num = scala.io.StdIn.readInt()
    var count=0
    var a=0
    var b=1
    println(s"Fibonacci series upto $num is :-")
    while(count<num){
      println(a) //0,1,1,2,
      val c=a+b
      a=b //1,1,2
      b=c //1,2,3
      count=count+1 //1,2
    }
  }

}
