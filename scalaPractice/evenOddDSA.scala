package scalaPractice

object evenOddDSA {
  def main(args:Array[String]):Unit={
val array=Array(1,6,8,9,4,7,2,5,89,95,64,33)
    val size=array.length

    println("\nmodified array: ")
    evenOddSeparate(array,size)
  }

  def evenOddSeparate(arr:Array[Int],len:Int): Unit = {
    var index = 0

    var a = new Array[Int](len)
    for (i <- 0 until len) {
      if (arr(i) % 2 == 0) {
        a(index) = arr(i)
        index = index + 1
      }
    }
    for (i <- 0 until len) {
      if (arr(i) % 2 != 0) {
        a(index) = arr(i)
        index = index + 1
      }
    }


    for (i <- 0 until len) {
      print(a(i) + " ")
    }
  }


}
