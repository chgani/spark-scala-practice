package in.olc.scala.scalaPractice

sealed trait Expr
case class Number(i:Int) extends Expr
case class Sum(expr1:Expr,expr2:Expr) extends Expr
case class Substract(expr1:Expr,expr2:Expr) extends Expr
case class Multiply(expr1:Expr,expr2:Expr) extends Expr
object evalcalc {
  def main(args:Array[String]):Unit={
println(eval(Sum(Number(10),Number(2))))
  }
  def eval(expr:Expr):Int={
    expr match{
      case Number(i)=>i
      case Sum(Number(x),Number(y))=>x+y
      case Substract(Number(x),Number(y))=>x-y
      case Multiply(Number(x),Number(y))=>x*y
    }
  }

}
