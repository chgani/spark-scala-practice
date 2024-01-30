package in.olc.scala.scalaPractice

object reverseString {

  def reverse(x:String):String={
    var reversed=""
    for(i<- x.length-1 to 0 by -1){
      reversed+=x(i)
    }
    reversed
  }
  def main(args:Array[String])={
    val input ="Hello World"
    val output=reverse(input)
    println(s"reverse of $input is $output")
  }
}
