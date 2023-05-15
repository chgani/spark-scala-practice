package in.olc.scala.sortingAlgos

//The quicksort algorithm is implemented by the quickSort object, which sorts arrays of numbers.
// Recursively dividing the array around a pivot element is how the algorithm operates.
// The array is divided so that all elements smaller than the pivot element are placed before the pivot
// element and all elements greater than the pivot element are placed after the pivot element.
// The pivot element is selected at random.
// Then, qsort is invoked recursively on the two subarrays. The array is not sorted until this operation is finished.
object quickSort {
  def qsort(arr:Array[Int],p:Int,q:Int):Array[Int]={
    //In order to prevent needless recursion and processing on subarrays that have already been sorted, the p<q condition is an essential
    // check that assures the qsort method only sorts subarrays that have more than one element.
    if(p<q){
     val mid=partition(arr,p,q)
      qsort(arr,p,mid-1) //this recursive call is for sorting elements before pivot element
      qsort(arr,mid+1,q) //this recursive call is for sorting elements after pivot element
    }
    arr
  }
  def partition(arr:Array[Int],p:Int,q:Int):Int={
    var i:Int=p
    //The pivot element is chosen randomly. In this case, the pivot element is 70
    //The pivot element serves as a dividing line for the array. All elements that are smaller than the pivot
    // element go in front of it, and all
    // elements that are larger than the pivot element go in back.
    //and it returns the index of pivot element at the end,which is dividing the array
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
