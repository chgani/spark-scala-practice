package in.olc.scala.scalaPractice

object ScalaProblemsDSA2 {
def main(args:Array[String]):Unit={
//println(wordCount("hi hello how are you hi hello happy hello how hi"))
  println(reverseFL(List(1,2,3,4,5)))
  println(List(1,2,3,4).map(x=>(x,x*2)))
  println(List(1,2,3,4).flatMap(x=>List(x,x*2)))
  println(myZip(List(1,2,3),List("a","b","c")))
  }
  def wordCount(text:String):List[(String,Int)]={
    text.split(" ").map((_,1)).groupBy(_._1).map{case(k,v)=>(k,v.map{_._2}.sum)}.toList.sortWith{
      case((w1,c1),(w2,c2))=>
        if(c1!=c2) c1<c2 else w1<w2
    }
  }
  def reverse(l:List[Int]):List[Int]={
    l match {
      case h ::tail =>reverse(tail):::List(h)
      case Nil => Nil
    }
  }
  def reverseFL(l:List[Int]):List[Int]={
    l.foldLeft(List.empty[Int]){(accemp,Listele)=>Listele+:accemp}
  }

//  def window(length:Int,li:List[Int]):List[List[Int]]={
//
//  }
  def myZip(l1:List[Int],l2:List[String]):List[(Int,String)]={

  l1.foldLeft(List.empty[(Int,String)]){(acc,element) => acc :+ (element,l2(acc.size))}

}
}
