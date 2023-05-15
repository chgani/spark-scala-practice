package in.olc.scala.linearSearchAlg

import scala.collection.mutable.ArrayBuffer

object findNumInArrayRecursion {
def findNum(arr:Array[Int],target:Int,index:Int):Boolean={
  if(arr(index)==target) true
  else index<arr.length-1 && findNum(arr,target,index+1)
}
  def findIndex(arr:Array[Int],target:Int,index:Int):Int={
    if(index==arr.length) -1
    else if(arr(index)==target) index
    else findIndex(arr,target,index+1)
  }
  def findIndexFromLast(arr:Array[Int],target:Int,index:Int):Int={
    if(index==(-1)) -1
    else if (arr(index)==target) index
    else findIndexFromLast(arr,target,index-1)
  }
  val buf=new ArrayBuffer[Int]()

  def findAllIndex(arr:Array[Int],target:Int,index:Int):Unit={
    if(index==arr.length) {
      return
    }
    if(arr(index)==target){
      buf+=index
    }
     findAllIndex(arr,target,index+1)
  }
  def main(args:Array[String])={
    val a=Array(-1,0,7,4,3,6,6)
    val target=6
    val result=findNum(a,target,0)
    println(result)
    val indexOfTarget=findIndex(a,target,0)
    println(s"the index of $target in given array is : $indexOfTarget")
    val indexOfTargetFromLast=findIndexFromLast(a,target,a.length-1)
    println(s"the index of $target from last in given array is : $indexOfTargetFromLast")
    findAllIndex(a,target,0)
    println(buf.mkString(" "))
  }
}
