package in.olc.scala.scalaPractice
//CS,IT,EC,ME
case class Student(id:Int,name:String,age:Int,branch:String)
object studentsBranchWise {
  def main(args:Array[String])={
    val l=List(Student(1,"Ram",18,"CS"),Student(2,"Sam",18,"ME"),Student(3,"Shyam",19,"CS"),
      Student(4,"Anand",18,"EC"),Student(5,"Kumar",20,"IT"),Student(6,"Krish",19,"ME"))
    println(splitStudents(l))
  }
  def splitStudents(l:List[Student]):(List[Student],List[Student],List[Student],List[Student])={
    val CSstudents=l.filter(x=>x.branch=="CS")
    val ITstudents=l.filter(x=>x.branch=="IT")
    val ECstudents=l.filter(x=>x.branch=="EC")
    val MEstudents=l.filter(x=>x.branch=="ME")
    (CSstudents,ITstudents,ECstudents,MEstudents)
  }

}
