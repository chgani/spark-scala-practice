package in.olc.scala.sortingAlgos

object selectionSort_2 {
  def selection_2(arr: Array[Int]) = {
    for (i <- 0 until arr.length) {
      val last = arr.length - 1
      val minIndex = getMinIndex(arr, i, last)
      swapping(arr, minIndex, i)
      println(s"pass $i: " + arr.mkString(" "))
    }
  }

  def getMinIndex(arr: Array[Int], start: Int, last: Int): Int = {
    var min = start
    for (i <- start+1 to last) {
      if (arr(min) > arr(i)) min = i
    }
    min
  }
  def swapping(arr: Array[Int], start: Int, end: Int): Unit = {
    val temp = arr(start)
    arr(start) = arr(end)
    arr(end) = temp
  }
def main(args:Array[String]):Unit={
  val arr=Array(25,-32,0,-99,89,78,65)
  selection_2(arr)
  println(arr.mkString(" "))
}
}