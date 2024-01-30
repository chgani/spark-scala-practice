package in.olc.scala.scalaPractice

object palindrome {
  def main(args:Array[String]):Unit={
  //  println("Enter a number: ")
    //val num=scala.io.StdIn.readInt()
    //if(num==reverseOfNum(num)) println(s"$num is a palindrome") else println(s"$num is not a palindrome")
    println(s"result is: ${isPalindrome(10)}")
  }
  def reverseOfNum(number:Int):Int={
    number.toString.reverse.toInt
  }

  def isPalindrome(x:Int):Boolean={
    var reversed=0
    var temp=x
    while(temp!=0){
      val rem=temp%10
      reversed=(reversed*10)+rem
      temp=temp/10
    }
    x==reversed
  }

}
