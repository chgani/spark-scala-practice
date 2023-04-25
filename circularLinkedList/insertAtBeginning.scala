package circularLinkedList

class node(var data:Int,var next:node)
class insertAtBeginning {
  var head:node=null
  def insert(new_data:Int): Unit ={

    val new_node=new node(new_data,head)
    if(head==null){
      head=new_node //if no element is there at first head =new_node
      head.next=head //since it is a circular linked list new_node next will be pointing to the head
    }
    else {
      var current:node=head  //suppose one node/element is there (say(data,next)=(5,20) ,initialize current variable with head
      while(current.next!=head){ //this condition is not true,because present node having
        current=current.next
      }
      new_node.next=head //say inserting (6,next)=> next=20
      current.next=new_node //(5,next) => here next will be address of (6,20)
      head=new_node //now head will become new node
    }
  }

  def print(): Unit = {
    var temp = head //temp variable is intialized with head
    if(temp!=null){
      do{
        println(temp.data + " ")
        temp=temp.next
      } while(temp!=head)
    }
  }
}
object circular{
  def main(args:Array[String])={
    val cList=new insertAtBeginning
    cList.insert(5)
    cList.insert(6)
    cList.insert(3)
    cList.insert(8)
    cList.print()

  }
}

