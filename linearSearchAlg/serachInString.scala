package linearSearchAlg

object serachInString {
  def main(args: Array[String]) = {
    val name = "intellij"
    val char = 'I'

    println(search(name.toUpperCase(), char.toUpper))
   // println(name.map(x=>Array(x)).flatten)
   // println(12.toString.map(_.asDigit).size)
    val result=search2(name,char)
    println(s"the status of $char is present in $name is :$result")

  }

  def search(str: String, ch: Char): Boolean = {
    val n = str.length
    if (n == 0) {
      false
    }
    else {
      for (i <- 0 until n) {
        if (ch == str.charAt(i)) {
          return true
        }
      }
      false
    }
  }

  def search2(str1: String, ch1: Char): Boolean = {
    val n = str1.length
    if (n == 0) {
      false
    }
    else {
      for (ch <- str1.toCharArray) {
        if (ch == ch1) {
          return true
        }
      }
      false
    }
  }
}