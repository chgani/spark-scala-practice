package in.olc.scala.sortingAlgos

object selectionSort {
  def main(args: Array[String]): Unit = {
    val arr = Array(50,38,45,79,19,27,29)
    println("Initial array:"+arr.mkString(" "))
    selection(arr)
    println(arr.mkString(" "))
  }
def selection(arr: Array[Int]) = {
    for (i <- 0 until arr.length) {
      val last = arr.length - i - 1
      val maxIndex = getMaxIndex(arr, 0, last)
      swapping(arr, maxIndex, last)
      println(s"pass $i: "+arr.mkString(" "))
    }
  }
def getMaxIndex(arr: Array[Int], start: Int, last: Int): Int = {
    var max = start
    for (i <- start to last) {
      if (arr(max) < arr(i)) max = i
    }
    max
}
def swapping(arr: Array[Int], start: Int, end: Int): Unit = {
    val temp = arr(start)
    arr(start) = arr(end)
    arr(end) = temp
  }
}
