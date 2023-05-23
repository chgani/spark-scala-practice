package in.olc.scala.scalaPractice

case class Employee(id:Int,email:String,salary:Salary,age:Int)
case class Salary(basic:Double,hra:Double,ta:Double)
object employeeAppraisal {
  def main(args:Array[String]):Unit={
    val emp=List(Employee(123,"ram@mail.com",Salary(20000,10000,2200),45),Employee(234,"sam@msd.com",Salary(30000,9000,1150),50),
      Employee(345,"han@hr.com",Salary(33000,4200,2000),51))
    appraisal(emp).foreach(println)

  }
  def appraisal(emp:List[Employee]):List[Employee]={
    emp.map{
      case Employee(id,email,Salary(basic,hra,ta),age) => val updatedBasic=basic*1.1
        val updatedhra=if(age>50) hra*1.2 else hra
        Employee(id,email,Salary(updatedBasic,updatedhra,ta),age)
        }
  }

}
