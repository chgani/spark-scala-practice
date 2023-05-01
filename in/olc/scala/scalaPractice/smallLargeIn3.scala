package in.olc.scala.scalaPractice

object smallLargeIn3 {
  def main(args: Array[String]): Unit = {
    println("Enter first number: ")
    val n1 = scala.io.StdIn.readInt()
    println("Enter second number: ")
    val n2 = scala.io.StdIn.readInt()
    println("Enter third number: ")
    val n3 = scala.io.StdIn.readInt()
val numbers=List(n1,n2,n3)
    val largest=numbers.reduce((x,y)=> if(x>y) x else y)
    val smallest=numbers.reduce((x,y)=> if(x<y) x else y)
    println(s"largest number is = $largest")
    println(s"smallest number is = $smallest")
  }
}
