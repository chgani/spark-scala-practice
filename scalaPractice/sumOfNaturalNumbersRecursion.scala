package scalaPractice

object sumOfNaturalNumbersRecursion {
  def main(args:Array[String]):Unit={
    println("Enter a number: ")
    val num = scala.io.StdIn.readInt()
    val res=sumOfNaturalRec(num,1)
    println(s"the sum of natural numbers from 1 to $num is =$res")
  }
def sumOfNaturalRec(n:Int,result:Int): Int ={
  if(n==1) result
  else n+sumOfNaturalRec(n-1,1)
}
}
