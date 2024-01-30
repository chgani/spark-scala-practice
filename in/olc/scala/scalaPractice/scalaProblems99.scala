package in.olc.scala.scalaPractice

object scalaProblems99 {
  def main(args:Array[String]):Unit={
val l=List(1,1,1,2,3,4,5,6,6,5,6)
    println(s"last element in given list is : ${lastDigit(l)}")
    println(s"penultimate element in given list is : ${penultimateDigit(l)}")
    val n=0
    println(s"the $n index element in list is :${nthElement(n,l)}")
    println(s"number of elements in given list are : ${noOfElements1(l)}")
    println(s"number of elements in given list by method 2 are : ${noOfElements2(l)}")
    println(s"reverse of given list :${reverseList(l)}")
    println(s"Given list is palindrome :${isPalindrome(l)}")
    println(s"${eleminateConsecutiveDups(l)} is the list after removing consecutive duplicates in given list")
    println(s"run length encoding of given list is : ${runLengthEncode(l)}")
  }
  def lastDigit(l:List[Int]):Int={
    l match{
      case h::Nil => h
      case _::tail =>lastDigit(tail)
      case _ => throw new NoSuchElementException
    }
  }
  def penultimateDigit(l:List[Int]):Int={
    l match {
      case h :: _ :: Nil => h
      case _ :: tail => penultimateDigit(tail)
      case _ => throw new NoSuchElementException
    }
  }
  def nthElement(n:Int,l:List[Int]):Int={
    (n,l) match{
      case (0,h :: _) =>h
      case (n,_ :: tail) => nthElement(n-1,tail)
      case (_,Nil) => throw new NoSuchElementException
    }
  }
  def noOfElements1(l:List[Int]):Int={
    var elements=0
    for(i<-0 to l.length-1){
      elements=elements+1
    }
    elements
  }
  def noOfElements2(l:List[Int]):Int={
    l match{
      case Nil => 0
      case _::tail => 1+noOfElements2(tail)
    }
  }
  def reverseList(l:List[Int]):List[Int]={
    l.foldLeft(List.empty[Int])((acc,ele)=>ele :: acc)
  }

  def isPalindrome(l: List[Int]): Boolean = {
    val re=l.foldLeft(List.empty[Int])((acc, ele) => ele :: acc)
    l==re
  }
  def eleminateConsecutiveDups(l:List[Int]):List[Int]= {
    l match {
      case Nil => Nil
      case h :: tail => h :: eleminateConsecutiveDups(tail.takeWhile(_ == h))
    }
  }
  def runLengthEncode(l:List[Int]):List[(Int,Int)]={
    if(l.isEmpty) Nil
    else {
      val (packed,next)=l.span(_==l.head)
      (packed.length,packed.head)::runLengthEncode(next)
    }
  }
}
