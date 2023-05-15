package in.olc.scala.recursion

object reverseNum {
var sum:Int=0
  def reverse1(n:Int):Int= {
    if (n == 0) sum
    else {
      val rem = n % 10
      sum = sum * 10 + rem
      reverse1(n / 10)
    }
  }

  def helper(n: Int, digits: Int): Int ={
    if(n%10==n) n
    else{
      val rem=n%10
      rem*math.pow(10,digits-1).toInt+helper(n/10,digits-1)
    }
  }

  def reverse2(n: Int): Int = {
val digits:Int=math.log10(n).toInt+1
    helper(n,digits)
  }
def palindrome(n:Int):Boolean={
   n==reverse2(n)
}
  def main(args:Array[String])={
    val n=8421
    val result=reverse1(n)
    println(result)
    val result2=reverse2(n)
    println(s"by second method: $result2")
    println(palindrome(n))
  }

}
