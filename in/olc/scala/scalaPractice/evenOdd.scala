package in.olc.scala.scalaPractice

object evenOdd {
  def main(args:Array[String]):Unit={
    println("Enter a number: ")
    val num=scala.io.StdIn.readInt ()
    if(num%2==0){
      println(s"$num is an even number")
    }
    else println(s"$num is a odd number")
  }

}
