package in.olc.scala.scalaPractice

import scala.collection.mutable.ArrayBuffer

object scalaProblems {
  def main(args: Array[String]) = {
    val result = show1(3, 4)
    println(result)
    val result2 = show2(3, 4)
    println(result2)
    println(reverse(List(1, 2, 3, 4, 5)))
    println(rotate(Array(1, 2, 3, 4, 5, 6), 3).toList)
    println(fill(2)(3))
    println(removeDup(List(1, 2, 2, 3, 2, 4, 3, 5, 5, 3, 8, 2, 1, 0)))
    println(wordCount("hello hi how are you hello hi"))
    println(merge(List(1, 4, 2, 5), List(1, 3, 5, 3)))
    println(zip(List(1, 2, 3), List("one", "two", "three")))
    println(removeOdd(Map("hi"->2,"hello"->3,"how"->2,"you"->5)))
    println(concatenateMaps(Map("hi"->2,"hello"->3),Map("how"->5,"are"->4,"hi"->3)))

  }

  def show1(f: Int, n: Int): List[Int] = {
    val x = new ArrayBuffer[Int]()
    for (i <- 1 to n) {
      x += i
    }
    x.toList
    val l = x.flatMap(_.toString * f).map(_.asDigit)
    l.toList
  }

  def show2(f: Int, n: Int): List[Int] = {
    val x = (1 to n).toList
    x.flatMap(i => List.fill(f)(i))
  }

  def reverse(l: List[Int]): List[Int] = {
    l match {
      case h :: tail => reverse(tail) ::: List(h)
      case Nil => Nil
    }
  }

  def reverseArray(arr: Array[Int], x: Int, y: Int): Array[Int] = {
    try {
      var start = x
      var end = y
      var temp: Int = 0
      while (start < end) {
        temp = arr(start)
        arr(start) = arr(end)
        arr(end) = temp
        start += 1
        end -= 1
      }
      arr
    }
    catch {
      case f: ArrayIndexOutOfBoundsException => Array()
    }
  }

  def rotate(arr: Array[Int], p: Int): Array[Int] = {
    var te = p
    val n = arr.length
    te = te % n
    reverseArray(arr, 0, te - 1)
    reverseArray(arr, te, n - 1)
    reverseArray(arr, 0, n - 1)
    arr
  }

  def fill(e: Int)(n: Int): List[Int] = {
    if (n <= 0) Nil
    else {
      e :: fill(e)(n - 1)
    }
  }

  def removeDup(l: List[Int]): List[Int] = {
    //  l.distinct
    var x = List.empty[Int]
    for (i <- l) {
      if (!x.contains(i)) x = x :+ i
    }
    x
  }

  def wordCount(str: String): Map[String, Int] = {
    str.split(" ").map((_, 1)).toList.groupBy(_._1).map { case (k, v) => k -> v.map {_._2}.sum
    }
  }

  //  def removeOdd(map1: Map[String, Int]): Map[String, Int] = {
  //    map1.map{case(k,v)=>k->if(v%2==0) v}
  //  }
  def merge(l1: List[Int], l2: List[Int]): List[Int] = {
    val newL = l1 ::: l2
    newL.sorted
  }

  def zip(l1: List[Int], l2: List[String]): List[(Int, String)] = {
    //l1.zip(l2)
    val newL = List.empty[(Int, String)]
    l1.foldLeft(newL) { (acc, element) => acc :+ (element, l2(acc.size)) }
  }

  def removeOdd(m:Map[String,Int]):Map[String,Int]={
    m.map{
      case(k,v)=>(k,if(v%2==0) v else 0)
    }.filter(x=>x._2!=0)
    }
  def concatenateMaps(map1:Map[String,Int], map2:Map[String,Int]):Map[String,Int]={
    val list1=map1.toList++map2.toList
    list1.groupBy(_._1).map{case(k,v)=>k->v.map{_._2}.sum}
  }

}
