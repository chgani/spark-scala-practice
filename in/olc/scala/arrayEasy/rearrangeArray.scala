package in.olc.scala.arrayEasy

//Rearrange array such that even positioned are greater than odd
object rearrangeArray {
  def main(args: Array[String]): Unit = {
    val arr = Array(97, 78, 56, 65, 32, 2, 4, 7, 5, 7, 9)
    val size = arr.length
    printArr(arr,size)
    rearrangeArrayPos(arr,size)
    println("\nmodified array is : ")
    printArr(arr,size)
  }


  def rearrangeArrayPos(arr: Array[Int], len: Int): Unit = {
    var temp = 0
    for (i <- 1 until len) {
      if (i % 2 == 0) {
        if (arr(i) > arr(i - 1)) {
          temp = arr(i)
          arr(i) = arr(i - 1)
          arr(i - 1) = temp

        }
      }
      else if (arr(i) < arr(i - 1)) {
        temp = arr(i)
        arr(i) = arr(i - 1)
        arr(i - 1) = temp
      }
    }
  }
  def printArr(arr: Array[Int], len: Int) = {
    for (i <- 0 until len) {
      print(arr(i) + " ")
    }
  }
}



