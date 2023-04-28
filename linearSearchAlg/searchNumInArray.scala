package linearSearchAlg

object searchNumInArray {
  def main(args: Array[String]) = {
    val array = Array(4, 6, 3, 7, 8, 9)
    search(array, 9)
  }

  def search(arr: Array[Int], target: Int) = {
    val n = arr.length
    if (n == 0) {
      println("array is not valid")
    }
    else {
      var found = false
      for (i <- 0 until n) {
        if (arr(i) == target) {
          print(s"the index of $target is :$i")
          found = true
        }
      }
      if(!found) print("element not found")
    }

  }
}
