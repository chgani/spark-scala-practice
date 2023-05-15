package in.olc.scala.binarySearchAlgo
//floor : greatest number that is less than or equal to the number
object floorOfNumber {

  def main(args: Array[String]) = {
    val arr = Array(1, 3, 4, 5, 7, 9, 12, 53, 56, 89)
    println(arr.mkString(" "))
    val target = 6
    val result = arr(searchFloor(arr, target))
    println(s"the greatest number which is less than or equal to $target in the given array is $result")
  }

  def searchFloor(arr: Array[Int], target: Int): Int = {
    var start: Int = 0
    var end = arr.length - 1
    while (start <= end) {
      val mid = start + (end - start) / 2
      if (target < arr(mid)) end = mid - 1
      else if (target > arr(mid)) start = mid + 1
      else return mid
    }
    end
  }

}
