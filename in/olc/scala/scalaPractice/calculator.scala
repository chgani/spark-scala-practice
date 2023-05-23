package in.olc.scala.scalaPractice

object calculator {
  def main(args:Array[String]):Unit={

println(calculation("+",2,3))
  }

  def requiredCalculation(n1:Double,n2:Double,ops:String):Double={
    ops match {
      case "+"=>n1+n2
      case "-"=>n1-n2
      case "*"=>n1*n2
      case "/"=>n1/n2
      case _=> throw new IllegalArgumentException("enter operations in (+,-,*,/)")
    }
  }
  def requiredCalc2(ops:String):(Double,Double)=>Double={
    ops match {
      case "+" => (n1,n2)=>n1 + n2
      case "-" => (n1,n2)=>n1 - n2
      case "*" => (n1,n2)=>n1 * n2
      case "/" => (n1,n2)=>n1 / n2
      case _ => throw new IllegalArgumentException("enter operations in (+,-,*,/)")
    }
  }
  def calculation(ops:String,n1:Double,n2:Double):Double={
    val toperform=requiredCalc2(ops)
    toperform(n1,n2)
  }

}
