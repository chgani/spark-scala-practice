package in.olc.scala.scalaPractice

object removeAdjacentletter {
  def main(args:Array[String]):Unit={
    val s="abbaadcs"
    print(s"result : ${removal(s)}")
  }
def removal(s:String):Int={
  val ch=s.split("")
  var count=0
  for(i<- 1 until ch.length){
    if(ch(i)==ch(i-1)) count=count+1
  }
  count
}
}
