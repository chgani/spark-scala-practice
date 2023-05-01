package in.olc.scala.scalaPractice

object calculateLibraryFine {
  def main(args:Array[String]):Unit={
    println("Enter number of days after the book is returned")
    val days=scala.io.StdIn.readInt()
    if(days<=7) {
      println(s"the book is returned after $days days. There is no fine")
    } else if (days>=5) {
      val upto5days = days - 7

    }
  }

}
