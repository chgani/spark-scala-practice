package in.olc.scala.sortingAlgos

object bubbleSort {
  def main(args: Array[String]) = {
    val arr = Array(5, 4, 3, 2, 1)
    bubble(arr)
    println(arr.mkString("[", ",", "]"))
  }

  def bubble(arr: Array[Int]): Unit = {
    var swapped: Boolean = false
    for (i <- 0 until arr.length) {
      swapped = false
      for (j <- 1 until arr.length - i) {
        if (arr(j) < arr(j - 1)) {
          val temp = arr(j)
          arr(j) = arr(j - 1)
          arr(j - 1) = temp
          swapped = true
        }
      }
      if (!swapped) {
        return
      }
    }
  }
}
