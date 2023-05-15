package in.olc.scala.binarySearchAlgo

object binarySearchNumInArray {
def main(args:Array[String])={
  val arr = Array(1,3,4,5, 7, 9, 12, 53,56, 89)
 println( arr.mkString(" "))
  val target =4
  val result = search(arr, target)
  if(result>0) println(s"the index of $target in given array is : $result")
  else println(s"The number $target is not found in the given array")

}
  def search(arr:Array[Int],target:Int):Int={
    var start:Int=0
    var end=arr.length-1
    while(start<=end){
      val mid=start+(end-start)/2
      if(target<arr(mid)) end=mid-1
      else if(target>arr(mid)) start=mid+1
     else return mid
    }
    -1
  }
}
