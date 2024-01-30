package in.olc.scala.patternQue

object patterns {
  def main(args: Array[String]): Unit = {
    pattern1(6)
    println("\n\n Pattern:2")
    pattern2(6)
    println("\n\n Pattern:3")
    pattern3(6)
    println("\n\n Pattern:4")
    pattern4(6)
    println("\n\n Pattern:5")
    pattern5(5)
    println("\n\n Pattern:28")
    pattern28(5)
    println("\n\n Pattern:30")
    pattern30(5)
  }
  def pattern1(n:Int):Unit={
    for(row<-1 to n){
      for(col<-1 to row){
        print("*")
      }
      println()
    }
  }

def pattern2(n:Int):Unit={
  for(row<-1 to n){
    for (col<- 1 to n){
      print("*")
    }
    println()
  }
}
  def pattern3(n:Int):Unit={
    for(row<- n to 1 by -1){
      for(col<-1 to row){
        print("* ")
      }
      println()
    }
  }

  def pattern311(n: Int): Unit = {
    for (row <- 1 to n) {
      for (col <- 1 to n-row+1) {
        print("* ")
      }
      println()
    }
  }

  def pattern4(n: Int): Unit = {
    for (row <- 1 to n) {
      for (col <- 1 to row) {
        print(col)
      }
      println()
    }
  }
  def pattern5(n:Int):Unit={
    for(row<-1 to 2*n-1){
      val c=if(row>n) 2*n-row else row
      for(col<-1 to c){
        print("* ")
      }
      println()
    }
  }

  def pattern28(n: Int): Unit = {
    for (row <- 1 to 2 * n - 1) {
      val totalColsInRow = if (row > n) 2 * n - row else row
      //val spaces=if(row>n) row-n else n-row
      val spaces=n-totalColsInRow
        for(s<- 0 to spaces){
          print(" ")
        }
       for (col <- 1 to totalColsInRow) {
        print("* ")
      }
      println()
    }
  }
  def pattern30(n:Int)={
    for(row<- 1 to n){
      val spaces = n - row
      for (s <- 0 to spaces) {
        print("  ")
      }
      for(col<-row to 1 by -1){
        print(" "+col)
      }
      for(col<-2 to row){
        print(" "+col)
      }
      println()
    }
  }
}


