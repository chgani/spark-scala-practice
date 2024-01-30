package in.olc.scala.scalaPractice

object replacePrimeNumberIndex {
  def main(args:Array[String]):Unit={
println(replace(5))
  }
def isPrime(n:Int):Boolean= {
  if (n == 0 || n == 1) false
  else if (n == 2) true
  else {
    var temp = 0
    for (i <- 2 until n) {
      if (n % i == 0) temp = temp + 1
    }
    if (temp > 0) false else true
  }
}
  def replace(n:Int):Int={
    if(isPrime(n)) {
      val primeNumbers=(2 to n).filter(isPrime(_))
      primeNumbers.indexOf(n)+1
    }
    else 0
}
}
