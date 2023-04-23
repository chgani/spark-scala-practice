package scalaPractice

class node(var data:Int,var next:node) //node is created with data and next as var
class linkedList {
  var head:node=null //intialize the head as none becuase head starts with null

    def insert(data1:Int)={
      val node1=new node(data1,head)  //new node is created with new data and current head ex: 9,null
      head=node1  //head is changes to new address when other node is added (This new node is then set as the
      //new head of the list)
    }
    def print():Unit={
      var temp=head //temp variable is intialized with head
      while(temp!=null){
        println(temp.data+" ")
        temp=temp.next
        }
    }
}

object list{
def main(args:Array[String])={
  val list1=new linkedList
  list1.insert(9)
  list1.insert(6)
  list1.insert(5)
  list1.print()
}
}

