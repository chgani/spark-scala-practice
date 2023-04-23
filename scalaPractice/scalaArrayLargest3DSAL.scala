package scalaPractice

object scalaArrayLargest3DSAL {
  def main(args:Array[String]):Unit={
    val arr=Array(56,96,99,87,45,12,35,62,62,61,41,0,4)
    val len=arr.length
    val largest=printLargest3Numbers1(arr)
    println(largest.mkString(","))
    printLargest3Numbers2(arr,len)
  }
  def printLargest3Numbers1(arr:Array[Int]):Array[Int]={
    val arr1=arr.distinct
    if(arr1.length<3) {
      println("Give valid input")
      arr1
    } else{
      val sortedArr=arr1.sortWith(_ > _)
      Array(sortedArr(0),sortedArr(1),sortedArr(2))

    }
  }
  def printLargest3Numbers2(arr:Array[Int],len:Int):Unit={
    var first,second,third=Int.MinValue
    if(len<3){
      println("Invalid Input")
    }
    for (i<- 0 until len){
      if(arr(i)>first){ //2,3,1,4
        third=second //2
        second=first //2,3
        first=arr(i) //2,3,4

      }
      else if(arr(i)>second){
        third=second
        second=arr(i)
      }
      else if(arr(i)>third){
        third=arr(i)
      }

    }
    println(s"three largest numbers are $first,$second and $third")

  }

}
