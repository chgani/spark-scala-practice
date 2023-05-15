package in.olc.scala.binarySearchAlgo
//if wew want to do something with sorted array go for binary search.

//ceiling : smallest number in an array which is greater than or equal to given number

object ceilingOfNumber {
def main(args:Array[String])={
    val arr = Array(1,3,4,5, 7, 9, 12, 53,56, 89)
   println( arr.mkString(" "))
    val target =9
    val result = arr(searchCeil(arr, target))
  println(s"the smallest number which is greater or equal to $target in the given array is $result")
}
    def searchCeil(arr:Array[Int],target:Int):Int={
      //if(target > arr(arr.length-1)){ return -1}
      var start:Int=0
      var end=arr.length-1
      while(start<=end){
        val mid=start+(end-start)/2
        if(target<arr(mid)) end=mid-1
        else if(target>arr(mid)) start=mid+1
       else return mid
      }
      start
    }
}
