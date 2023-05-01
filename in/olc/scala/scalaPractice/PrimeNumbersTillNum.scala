package in.olc.scala.scalaPractice

object PrimeNumbersTillNum {
  def main(args: Array[String]): Unit = {
    println("Enter a number: ")
    val num = scala.io.StdIn.readInt()
    val listOfPrimes=primeNumbers(num)
    println(s"The primenumbers from 1 to $num are $listOfPrimes")
    }
  def isPrime(num:Int):Boolean={
    if(num<=1) return false
    for(i<-2 to math.sqrt(num).toInt){
      if(num%i==0) return false
    }
    true
  }
  def primeNumbers(n:Int):List[Int]={
    var primeList:List[Int]=List()
    for(i<-2 to n){
      if(isPrime(i)) primeList=i::primeList
    }
    primeList.reverse
    //primeList.toList
  }
}
