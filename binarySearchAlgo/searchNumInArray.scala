package binarySearchAlgo

object searchNumInArray {
  def main(args:Array[String])={
    val arr=Array(3,5,7,9,12,56,89,53,1,4)
    val target=9
val result=search(arr,target)
    println(result)

  }
  def search(arr:Array[Int],target:Int): Boolean ={
    val arr1=arr.sorted
    val n=arr1.length
    val in=n/2
    val reference=arr1(in)
println(reference)
    if(reference==target){
      return true
    }
    if(target<reference) {
      for (i <- 0 until in) {
        if (arr1(i) == target) {
          return true
        }

      }
      false
    }
        else if(target>reference){
          for(i<- in until n){
            if(arr1(i)==target){
              return true
            }
          }
      false
        }
        else false
      }

    }



