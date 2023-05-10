package in.olc.scala.sortingAlgos

object insertionSort {
  def main(args:Array[String]):Unit={
    val arr=Array(0,-56,-28,8,99,56)
    insertion(arr)
    println(arr.mkString(" "))
  }
def insertion (arr:Array[Int]):Array[Int]={
  for(i<-0 to arr.length-2){
    var j=i+1
//    while(j>0) {
//      if (arr(j) < arr(j - 1)) {
//        swap(arr, j, j - 1)
//        j -= 1
//      } else {
//        return
//      }
//    }
    while (j>0 && arr(j)<arr(j-1)){
      swap(arr,j,j-1)
      j-=1
    }
  }
arr
}
  def swap(arr:Array[Int],a1:Int,a2:Int)={
     val temp=arr(a1)
      arr(a1)=arr(a2)
      arr(a2)=temp

  }
}
