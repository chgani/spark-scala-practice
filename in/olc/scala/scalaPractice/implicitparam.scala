package in.olc.scala.scalaPractice

object implicitparam {
  implicit class withSquare(n:Int) {
    def square: Int = n * n
  }

  def main(args:Array[String]):Unit={
    print(2.square)
  }
}
