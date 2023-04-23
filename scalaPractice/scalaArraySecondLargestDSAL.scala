package scalaPractice

object scalaArraySecondLargestDSAL {
  def main(args:Array[String]):Unit={
val array=Array(23,34,56,32,23,87,98,56)
    val secLargest1=secondlargestSimple1(array)
    println(s"the second largest element (by method 1) in given array is $secLargest1")
    val size=array.length
    secondlargestSimple2(array,size)

  }
def secondlargestSimple1(arr:Array[Int]):Int={
  val arrDistinctSorted=arr.distinct.sortWith(_ > _)
  //val len=arrDistinctSorted.length
  arrDistinctSorted(1)
}
  def secondlargestSimple2(arr:Array[Int],len:Int):Unit={
    //val arrdistinct=arr.distinct
    if(len<2) println("Invalid input")
    var largest,second=Int.MinValue
    for(i<- 0 until len-1){
      if(arr(i)>largest) { //23,34,56,32,23,87,98,56
        second = largest //minvalue,23,34
        largest = arr(i) //23,34,56
      }
        else if(arr(i)>second && arr(i)!=largest){
          second=arr(i)
        }
      }
    println(s"the second largest element (by method 2) in given array is $second")
    }


}
