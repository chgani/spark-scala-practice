package in.olc.scala.recursion
//find given array is sorted or not
object sorted {
  def sortedOrNot(arr: Array[Int], index: Int): Boolean = {
    if (index == arr.length - 1) true
    else arr(index) < arr(index + 1) && sortedOrNot(arr, index + 1)
  }

  def main(arr: Array[String]) = {
    val a = Array(1,2,3,4,5,6,1)
    val result=sortedOrNot(a,0)
    if(result==true) println("Given array is sorted")
    else println("Given array is not sorted")
  }
}