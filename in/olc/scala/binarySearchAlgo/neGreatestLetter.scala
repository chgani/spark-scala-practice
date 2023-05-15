package in.olc.scala.binarySearchAlgo
//find smallest letter greater than the target
//https://leetcode.com/problems/find-smallest-letter-greater-than-target/
object neGreatestLetter {
  def nextGreatestLetter(arr:Array[Char],target:Char):Char={
    val n=arr.length
    var start=0
    var end=n-1
    while(start<=end){
      val mid=start+(end-start)/2
      if(target.toInt<arr(mid).toInt) end=mid-1
      else start=mid+1
    }
    arr(start%n)
  }
  def main(args:Array[String])={
    val a=Array('c','f','j')
    val target='k'
    val result=nextGreatestLetter(a,target)
    println(result)
  }

}
