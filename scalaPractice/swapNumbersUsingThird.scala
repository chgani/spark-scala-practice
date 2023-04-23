package scalaPractice

object swapNumbersUsingThird {
  def main(args:Array[String]):Unit={
    var a=10
    var b=20
    println("Initial values of a & b")
    println(s"a=$a")
    println(s"b=$b")
    var temp=0
    temp=a
    a=b
    b=temp
    println("values of a and b after swapping")
    println(s"a=$a")
    println(s"b=$b")
  }

}
