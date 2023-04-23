package scalaPractice

object smallLargeNumber {
  def main(args:Array[String]):Unit={
    println("Enter first number: ")
    val n1=scala.io.StdIn.readInt()
    println("Enter second number: ")
    val n2 = scala.io.StdIn.readInt()
    if(n1<n2){
      println(s"small number is = $n1")
      println(s"Large number is = $n2")
    }
    else {
      println(s"small number is = $n2")
      println(s"Large number is = $n1")
    }
  }

}
