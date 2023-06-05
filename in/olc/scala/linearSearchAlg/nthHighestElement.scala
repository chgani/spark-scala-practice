package in.olc.scala.linearSearchAlg

object nthHighestElement {
  def main(args:Array[String]):Unit={
val a=Array(1,3,2,4,0,-4,8,10,34,222,453,45)
    nthHighest(a,1)
  }
  def nthHighest(arr:Array[Int],n:Int)={
    val len=arr.length
    if(n<0||n>len||len==0) {
      println("Invalid Input")
    }
    else{
      var acc=0
      var max=Integer.MIN_VALUE
      var nthMax=Integer.MIN_VALUE
      for(i<- 0 until len){
        if(arr(i)>max){
          nthMax=max
          max=arr(i)
          acc+=1
        }
        else if(arr(i)<max && nthMax<arr(i)) {
          nthMax = arr(i)
        }
      }
      if(acc<n-1) println("there are no sufficient elements to check nth max")
      else println(s"the $n-th maximum element in the given array is : $nthMax")
    }
  }
}
