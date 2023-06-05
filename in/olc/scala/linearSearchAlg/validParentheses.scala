package in.olc.scala.linearSearchAlg

object validParentheses {
  def isValid(s: String): Boolean = {
val len=s.length
    if(len==0 || len%2!=0) false
    else {
      for(i<-0 until len by 2) {
        if (s.charAt(i) == s.charAt(i + 1)) return true
      }
      false
    }
  }
  def main(args:Array[String]):Unit={
    val s="()[]{}()"
    println(isValid(s))
  }
}
