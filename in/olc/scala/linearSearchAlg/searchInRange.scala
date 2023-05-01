package in.olc.scala.linearSearchAlg

//serach the number in the range of index from ind1 until ind2
object searchInRange {
def main(args:Array[String])={
  val array=Array(2,4,5,6,7,3,8,9)
  searchRange(array,3,0,4)
}
  def searchRange(arr:Array[Int],target:Int,index1:Int,index2:Int)={
    val n=arr.length
    if(n==0){
      println("array is not valid")
      }
    else {
      var found=false
      for(i<-index1 to index2){
        if(arr(i)==target){
          println(s"the index of $target is:$i")
          found=true
        }
      }
      if(!found) println("the number is not present in given range")
    }
  }
}
