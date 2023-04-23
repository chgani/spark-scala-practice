package scalaPractice

object circleProblem {
  def main(args:Array[String]):Unit={
    println("Enter radius of circle in centimeters")
    val radius=scala.io.StdIn.readFloat()
    println(s"radius=$radius cm")
    val area=circleProblem.area(radius)
    val circum=circumference(radius)
    println(s"Araa of the circle with radius $radius cm is = $area cm^2")
    println(s"Circumference of the circle with radius $radius cm is =$circum cm")

  }
def area(r:Float):Double={
  Math.PI*r*r
}
  def circumference(r:Float):Double={
    2*Math.PI*r
  }
}
