package scalaPractice

object diagonalRectangle {
  def main(args:Array[String]):Unit={
    println("Enter length of rectangle in cm:")
    val l=scala.io.StdIn.readFloat()
    println("Enter breadth of rectangle in cm:")
    val b = scala.io.StdIn.readFloat()
    val diagonal=math.round(math.sqrt((l*l)+(b*b))*math.pow(10,2))/math.pow(10,2)
    println(s"the diagonal of rectangle with length $l cm and breadth $b cm is =$diagonal cm")
  }

}
