package linearSearchAlg

object searchNumInArray {
  def main(args: Array[String]) = {
    val array = Array(4, 6, 3, 7, 8, 9)
    val target1=9
    val target2=8
    search(array, target1)
    println("\n")
   println(presentOrNot(array,target2))
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
  def presentOrNot(arr:Array[Int],target:Int):Boolean={
    val n = arr.length
    if (n == 0) {
      println("array is not valid")
      false
    }
    else {
      for(element<-arr){
        if(element==target){
          return true
        }
      }
      false
    }
  }
}
