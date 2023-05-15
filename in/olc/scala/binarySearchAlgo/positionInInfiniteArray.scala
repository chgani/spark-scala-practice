package in.olc.scala.binarySearchAlgo
//Find position of an element in a sorted array of infinite numbers
object positionInInfiniteArray {

def findPos(arr:Array[Int],target:Int)={
  var start=0
  var end=1
  while(target>arr(end)){
    val newStart=end+1
    end=end+(end-start+1)*2 //previous end +range *2
    start=newStart
  }
  search(arr,target,start,end)
}

  def search(arr:Array[Int],target:Int,s:Int,e:Int):Int={
    var start=s
    var end=e
    while(start<=end){
      //val mid=start+(end-start)/2
      val mid=start+((end-start)>>1) //bit shifting
      if(target<arr(mid)) end =mid-1
      else if(target>arr(mid)) start=mid+1
      else return mid
    }
    -1
  }
  def main(args:Array[String]):Unit={
    val arr=(1 to 100 by 1).toArray
    val target=49
    val result=findPos(arr, target)
    println(result)
  }
}
