package in.olc.scala.linearSearchAlg

object twoSum {
def twosum(nums:Array[Int],target:Int):Array[Int]={
  val n:Int=nums.length-1
  for(i<-0 to n;j<-i+1 to n){
      val sum = nums(i) + nums(j)
      if (sum == target) {
        return Array(i, j)

    }
  }
  Array.emptyIntArray
}
  def main(args:Array[String])={
    val arr=Array(1,2,3,4,5,6,7,8,9)
    val target=11
    val result=twosum(arr,target)
    println(result.toList)
  }
}
