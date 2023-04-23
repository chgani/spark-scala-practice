package scalaPractice

object scalaDSALpushZerosToEnd {
  def main(args:Array[String]):Unit={
val array=Array(1,0,2,3,0,5,0,7,0,9,0,5,2,6,0,8,7,0,7,8,9)
    val size=array.length
    println("original array is:")
    printArray(array,size)
    pushzeros1(array,size)
    println("\nmodified array is:")
    printArray(array,size)
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
}
