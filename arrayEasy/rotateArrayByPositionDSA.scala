package arrayEasy

object rotateArrayByPositionDSA {
  def main(args:Array[String])={
    val array=Array(1,2,3,4,5,6,7)
    rotate(array,5)
    printarray(array)
  }
  def reverseArray(arr:Array[Int],s:Int,e:Int): Unit ={
    var temp=0
    var start=s
    var end=e
    while(start<end){
      temp=arr(start)
      arr(start)=arr(end)
      arr(end)=temp
      start+=1
      end-=1
    }
  }
  def rotate(arr:Array[Int],d:Int)={
    var re=d
    val n=arr.length
    re=re%n
    reverseArray(arr,0,re-1)
    reverseArray(arr,re,n-1)
    reverseArray(arr,0,n-1)
  }
  def printarray(arr:Array[Int])={
    val n=arr.length
    for(i<-0 until n){
      println(arr(i))
    }
  }
}
