package in.olc.scala.recursion

object sumOfDigitsOfNumber {
  def sumofDigits(n:Int):Int={
    var temp=n
    var sum=0
    var i=n.toString.length
    while(i>0){
      sum=sum+temp%10
      temp=temp/10
      i-=1
    }
    sum
  }
  def sumOfDigits_2(n:Int):Int={
    if(n==0) 0
    else {
      var sum=n%10
      sum+sumOfDigits_2(n/10)
    }
  }
  def prod(n:Int):Int={
    if(n%10==n) n
    else (n%10)*prod(n/10)
  }
  def main(args:Array[String]):Unit={
    val n=1564
    val result=sumofDigits(n)
    println(s"the sum of digits of $n is : $result")
    val result2=sumOfDigits_2(n)
    println(s"the sum of Digits of $n by method 2 is: $result2")
    val prod_res=prod(n)
    println(s"the product of digits of $n is: $prod_res")
  }

}
