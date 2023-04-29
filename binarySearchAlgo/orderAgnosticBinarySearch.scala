package binarySearchAlgo
//the given array is sorted
//if we dont know whether the array is sorted in descending/ascending
//in this case we can take any two elements and compare and get to know ascending/descending provided array is sorted
//best way we can check first and last element
object orderAgnosticBinarySearch {
  def main(args:Array[String])={
    //val arr=Array(1,2,3,4,5,6,7,8,9)
    val arr=Array(23,20,19,14,11,8,7,3)
    val result=search(arr,23)
    println(result)
  }
def search(arr:Array[Int],target:Int):Int={
  var start=0
  var end=arr.length-1
  val isAsc:Boolean=arr(start)<arr(end)
  while(start<=end){
    val mid=start+(end-start)/2
    if(arr(mid)==target) return mid
    if(isAsc){
      if(target<arr(mid)) end=mid-1
      if(target>arr(mid)) start=mid+1
    }
    else{
      if(target>arr(mid)) end=mid-1
      if(target<arr(mid)) start=mid+1
    }
  }
  -1
}
}
