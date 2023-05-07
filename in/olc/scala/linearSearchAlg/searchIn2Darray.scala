package in.olc.scala.linearSearchAlg

object searchIn2Darray {
  def main(args: Array[String]) = {
    val arr = Array(Array(2, 44, 6, 37, 8), Array(5, 54, 65, 93, 80))
    val target=8
val result=search2D(arr,target)
    //result.mkString(",").map(x=>println(s"the number $target is found in ${x(0)} th array in ${x(1)} th index "))
    println(s"result is $result")
    result match{
      case Some((i,j))=> println(s"the number $target is found in $i-th array in $j-th index")
      case None=>println(s"the number $target is not found in the given array")
    }
    val maximum =maxIn2DArray(arr)
    println(s"The maximum value in given 2D array is $maximum")
  }
  def search2D(arr:Array[Array[Int]],target:Int):Option[(Int,Int)]={
  for(i<-0 until arr.length){
    for(j<-0 until arr(i).size){
      if(arr(i)(j)==target){
        return Some((i,j))
     }
    }
  }
   None
  }
  def maxIn2DArray(arr:Array[Array[Int]]):Int={
    var max=Integer.MIN_VALUE
    for(i<-0 until arr.length){
      for(j<-0 until arr(i).size) {
        if(arr(i)(j)>max){
          max=arr(i)(j)
        }
      }
    }
    max
  }
}
