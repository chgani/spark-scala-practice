package scalaPractice

object sumOfDigits {
def main(args:Array[String]):Unit={
  println("Enter a number: ")
  val num = scala.io.StdIn.readInt()
  val x=num.toString.map(_.asDigit).toList
  println(x.sum)
}
}
