package in.olc.scala.arrayEasy

object scalaDSALpushZerosToEnd {
  def main(args:Array[String]):Unit={
    val array=Array(1,0,2,3,0,5,0,7,0,9,0,5,2,6,0,8,7,0,7,8,9)
    val size=array.length
    println("original array is:")
    printArray(array,size)
    println(s"\nnumber of zeros in given array are: ${countZeros(array,size)}")
    pushzeros1(array,size)
    println("\nmodified array is:")
    printArray(array,size)
    println("\nremove zeros")
    println(removeZeros(array,size).mkString(" "))

  }

  def pushzeros1(arr:Array[Int],len:Int)={
    var count=0
    for (i<- 0 until len){
      if(arr(i)!=0){
        arr(count)=arr(i)
        count=count+1
      }
    }
    for(i<- count until len){
      arr(i)=0
    }
  }
  def printArray(arr:Array[Int],len:Int)={
    for(i<-0 until len){
      print(arr(i)+" ")
    }
  }
  def removeZeros(arr:Array[Int],len:Int):Array[Int]={
    var arr2=Array.emptyIntArray
    for(i<-0 until len){
      if(arr(i)!=0) {
        arr2=arr2:+arr(i)
      }
    }
    arr2
  }

  ///count the zeros in an array
  def countZeros(arr:Array[Int],len:Int)={
    var count=0
    for(i<-0 until len){
      if(arr(i)==0) count=count+1
    }
    count
  }
}
