package in.olc.scala.scalaPractice

object mergeMaps {

  def merge(m1:Map[String,Int],m2:Map[String,Int]):Map[String,Int]={
    val m_list=m1.toList++m2.toList //List((Hi,1), (Hello,2), (How,1), (are,1), (you,3), (Hi,5))
    m_list.groupBy(x=>x._1) // Map(Hello->List((Hello,2)),Hi->List((Hi,2),(Hi,5)))
      .map{case(k,v)=>k->v.map(_._2).sum}
  }
  def main(args:Array[String])={
    val m1=Map("Hi"->1,"Hello"->2,"How"->1)
    val m2=Map("are"->1,"you"->3,"Hi"->5)
 // val m3=m1++m2.map{case(k,v) =>k->(v+m1.getOrElse(k,0))}
  println(m1)
  println(m2)
    println(merge(m1,m2))
   // println(m1.toList++m2.toList)
   // println((m1.toList++m2.toList).groupBy(_._1))
  //println(m3)
  }

}
