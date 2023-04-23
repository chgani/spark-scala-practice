package scalaPractice

object primeNumber {
  def main(args:Array[String]):Unit={
    println("Enter a number: ")
    val num=scala.io.StdIn.readInt()
    val l=(2 to num-1 by 1).toList
    for(i<-l){
     if(num%i==0) {
       println(s"$num is not a prime number")
     } else {
       println(s"$num is a prime number")
     }
    }
  }
}
