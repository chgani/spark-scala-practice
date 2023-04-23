package scalaPractice

object swapNumbersWithoutUsingThird {
  def main(args: Array[String]): Unit = {
    var a = 10
    var b = 20
    println("Initial values of a & b")
    println(s"a=$a")
    println(s"b=$b")
    a=a+b
    b=math.abs(a-b)
    a=math.abs(a-b)
    println("values of a and b after swapping")
    println(s"a=$a")
    println(s"b=$b")
  }
}
