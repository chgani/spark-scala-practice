package scalaPractice

object deseriumNumber {
  def main(args: Array[String]): Unit = {
    println("Enter a number: ")
    val num = scala.io.StdIn.readInt()
    val result=num.toString.map(_.asDigit).toList.map(x=>x*x).sum
    if(num==result) println(s"$num is a Deserium number") else println(s"$num is not a Deserium number")
  }


}
