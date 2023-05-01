package in.olc.scala.scalaPractice

object HCFbyRecursion {
  def main(args:Array[String]):Unit={
      println("Enter two numbers: ")
      val num1 = scala.io.StdIn.readInt()
      val num2 = scala.io.StdIn.readInt()
    val result=HCFrecur(num1,num2)
    println(s"the HCF of $num1 and $num2 is = $result")
  }
def HCFrecur(n1:Int,n2:Int):Int={
if(n2==0) n1
else HCFrecur(n2,n1%n2)
}
}
