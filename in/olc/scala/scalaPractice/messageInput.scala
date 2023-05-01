package in.olc.scala.scalaPractice

object messageInput {
  def main(args:Array[String]):Unit={
    println("Enter a number: ")
    val num=scala.io.StdIn.readInt()
    num match {
      case 1 => println("I can dance")
      case 2 =>println("I can sing")
      case 3 => println("I can swim")
      case _=> println("Wrong option")
    }
  }
}
