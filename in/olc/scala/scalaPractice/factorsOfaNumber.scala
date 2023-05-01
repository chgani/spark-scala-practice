package in.olc.scala.scalaPractice

object factorsOfaNumber {
  def main(args: Array[String]): Unit = {
    println("Enter a number: ")
    val num = scala.io.StdIn.readInt()

    for(i<-1 to num) {
      if (num % i == 0) println(i)
    }
  }
}
