package in.olc.scala.binarySearchAlgo
//https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
//34. Find First and Last Position of Element in Sorted Array
object searchElementPositions {
  def searchRange(nums: Array[Int], target: Int): Array[Int] = {
    val ans=Array(-1,-1)
    ans(0)=search(nums,target,true)
    ans(1)=search(nums,target,false)
    ans
  }
  def search(a:Array[Int],target:Int,firstIndex:Boolean):Int={
    var ans=(-1)
    var start=0
    var end=a.length-1
    while(start<=end){
      val mid=start+(end-start)/2
      if(target<a(mid)) end=mid-1
      else if(target>a(mid)) start=mid+1
      else{
        ans=mid
        if(firstIndex) end=mid-1
        else start=mid+1
      }
    }
    ans
  }

  def main(args:Array[String]):Unit={
    val arr=Array(1,3,4,6,6,7,7,7,7,7,8,9,9,9,9)
    val target=10
    val result=searchRange(arr,target)
    println(result.toList)
  }
}
