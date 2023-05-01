package in.olc.scala.scalaPractice

object HCFofTwoNumbers {
def main(args:Array[String]):Unit={
  println("Enter two numbers: ")
  val num1 = scala.io.StdIn.readInt()
  val num2 = scala.io.StdIn.readInt()
  val result=compute(num1,num2)
  println(s"the HCF of $num1 and $num2 is = $result")
}
  var smaller:Int=0
  var hcf:Int=0
  def compute(n1:Int,n2:Int)={
    if(n1>n2) {
      smaller=n2
    }
    else {
      smaller=n1
    }
    for(i<-1 to smaller+1){
      if(n1%i==0 && n2%i==0){
        hcf=i
      }
    }
hcf
  }
  }

