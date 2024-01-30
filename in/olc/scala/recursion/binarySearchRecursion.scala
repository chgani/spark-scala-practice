package in.olc.scala.recursion

class binarySearchRecursion {
  def search(arr:Array[Int],target:Int,start:Int,end:Int):Int={
    if(start>end) return -1
    val mid=start+(end-start)/2
    if(target<arr(mid)) search(arr,target,start,mid-1)
   else if(target>arr(mid)) search(arr,target,mid+1,end)
    else mid
  }

}
object bsRecursion{
  def main(args:Array[String]):Unit={
    val a=Array(1,4,5,6,24,65,78,86,88,91,95)
    val target=22
    val bs=new binarySearchRecursion
   val result= bs.search(a,target,0,a.length-1)
    println(s"The index of $target in given array is: $result")
  }
}