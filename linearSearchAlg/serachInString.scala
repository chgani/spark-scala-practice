package linearSearchAlg

object serachInString {
  def main(args:Array[String])={
    val name="intellij"
    val char='I'
    println(search(name.toUpperCase(),char.toUpper))

  }
  def search(str:String,ch:Char):Boolean={
    val n=str.length
    if(n==0){
      false
    }
    else {
      for(i<- 0 until n){
        if(ch==str.charAt(i)){
         return true
        }
      }
      false
    }
  }

}
