package in.olc.scala.scalaPractice

object charCountInAword {

  def charCount(s:String)={
    var charMap:Map[Char,Int]=Map[Char,Int]().withDefaultValue(0)
    val givenWord=s.toLowerCase
    for (char <- givenWord){
      charMap+=(char->(charMap(char)+1))
    }

    for ((key,value) <- charMap){
      println(s"$key : $value")
    }
  }

  def main(args:Array[String]):Unit={
    val word="Hello World"
    charCount(word)
  }

}
