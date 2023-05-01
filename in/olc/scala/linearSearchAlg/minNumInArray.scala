package in.olc.scala.linearSearchAlg

object minNumInArray {
  def main(args:Array[String])={
    val array=Array(2,3,-1,5,6,87)
    min(array)
  }
  def min(arr:Array[Int])={
    val n=arr.size
    if(n==0){
      println("Array is not valid")

    }
    else{
      var ans:Int=arr(0)
      for(i<-1 until n){

        if(arr(i)<ans){
          ans=arr(i)
        }
      }
      println(s"the minimum number in given array is :$ans")
    }

  }
}
