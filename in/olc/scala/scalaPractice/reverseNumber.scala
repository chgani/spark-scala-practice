package in.olc.scala.scalaPractice

object reverseNumber {
  def reverse(n: Int): Int = {
    val IntMax = Int.MaxValue
    val IntMin = Int.MinValue

    var reversed = 0
    var num=n
    while (num!= 0) {
      val rem = num % 10
      num = num / 10
      if (reversed>(IntMax-rem)/10|| reversed<(IntMin-rem)/10) {
        return 0
      }
      reversed = (reversed * 10) + rem

    }
    reversed
  }

  def main(args:Array[String]):Unit={
    println("Enter a number: ")
    val number=scala.io.StdIn.readInt()
    println(reverse(number))
  }
}
