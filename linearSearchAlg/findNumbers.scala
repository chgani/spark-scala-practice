package linearSearchAlg
//find numbers in a given array that are having even number of digits
object findNumbers {
def main(args:Array[String])={
val array=Array(2,56,78,987,67,3242,5)
  find(array)
  println("second method")
  findNumbers2(array).foreach(println)
}
  def find(arr:Array[Int]):Unit={
    for(i<-0 until arr.length){
      val len=arr(i).toString.map(_.asDigit).size
      if(len%2==0){
        println(arr(i)+ " ")
      }
    }
   // println("Numbers not found")
  }
  //second method
  def findNumbers2(arr:Array[Int])={
  //  var count=0
    var result=Array[Int]()
    for(i<-0 until arr.length){
      if(even(arr(i))){
        result=result:+arr(i)
      }
    }
    result
  }
  def even(n:Int):Boolean={
    val numberofDigits=digits(n)
    if(numberofDigits%2==0){
      return true

    }
    false
  }
  def digits(num:Int):Int={
    var count=0
    var n=num
    while(n>0){
      count=count+1
      n=n/10
    }
    count
  }
}
