package in.olc.scala.binarySearchAlgo

object binarySearchNumInArray {
def main(args:Array[String])={
  val arr = Array(3, 5, 7, 9, 12, 56, 89, 53, 1, 4)
  val a=arr.sorted
 println( a.mkString(" "))
  val target =4
  val result = search(arr, target)
  if(result>0) println(s"the index of $target in given array is : $result")
  else println(s"The number $target is not found in the given array")

}
  def search(arr:Array[Int],target:Int):Int={
    val arr1=arr.sorted
    var start:Int=0
    var end=arr.length-1
    while(start<=end){
      val mid=start+(end-start)/2
      if(target<arr1(mid)) end=mid-1
      else if(target>arr1(mid)) start=mid+1
     else return mid
    }
    -1
  }
}
