package in.olc.scala.sortingAlgos

import scala.collection.mutable.ArrayBuffer

object mergeSort {
  def main(args:Array[String]):Unit={
    val arr=Array(26,-1,23,65,99,-100,25,63,98,77,54)
    val start=0
    val end=arr.length-1
    val result=mergeSort(arr,start,end)
    println(result.toList)
  }
  def mergeSort(arr:Array[Int],start:Int,end:Int):Array[Int]= {
    if (start == end) {
      return arr
    }
    else if (start < end) {
      val mid = start + (end - start) / 2
      mergeSort(arr, start, mid)
     mergeSort(arr, mid + 1, end)
      merging(arr, start, mid, end)
      arr
    }
    else{
      Array[Int]()
    }
  }
  def merging(arr: Array[Int], s: Int, m: Int, e: Int):Array[Int]={
    val l1=m-s+1 //number of elements in left subarray(s,mid)
    val l2=e-m //number of elements in right sub arrar(m+1,e)
    val leftSub=new ArrayBuffer[Int]()
    val rightSub=new ArrayBuffer[Int]()
    for(i<-0 until l1){
      leftSub+=arr(i+s)
    }
    for(j<- 0 until l2){
      rightSub+=arr(j+m+1)
    }
    var p,q=0
    var k=s
    while(p<l1 && q<l2){
      if(leftSub(p)<rightSub(q)){
        arr(k)=leftSub(p)
        p=p+1
      }
      else {
        arr(k)=rightSub(q)
        q+=1
      }
      k+=1
    }
    //suppose after comparing some elements are left in leftsubarray =>copying entire elements from leftsubarray
    while(p<l1){
      arr(k)=leftSub(p)
      p+=1
      k+=1
    }
    //suppose after comparing some elements are left in rightsubarray =>copying entire elements from rightsubarray
    while(q<l2){
      arr(k)=rightSub(q)
      q+=1
      k+=1
    }
    arr
  }
}


