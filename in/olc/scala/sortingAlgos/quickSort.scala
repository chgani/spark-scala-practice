package in.olc.scala.sortingAlgos

object quickSort {
  def qsort(arr:Array[Int],p:Int,q:Int):Array[Int]={
    if(p<q){
     val mid=partition(arr,p,q)
      qsort(arr,p,mid-1)
      qsort(arr,mid+1,q)
    }
    arr
  }
  def partition(arr:Array[Int],p:Int,q:Int):Int={
    var i:Int=p
    val pivot=arr(p)
    for(j<- i+1 to q){
      if(arr(j)<=pivot){
        i=i+1
        swap(arr,i,j)
      }
    }
    swap(arr,i,p)
    return i
  }
  def swap(arr:Array[Int],x:Int,y:Int):Unit={
    val temp=arr(x)
    arr(x)=arr(y)
    arr(y)=temp
  }

  def main(args:Array[String])={
    val arr=Array(70,57,27,99,85,47,123,34,147)
    qsort(arr,0,arr.length-1)
    println(arr.mkString(" "))
  }
}
