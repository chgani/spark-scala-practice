package doublyLinkedList
class node1(var data:Int,var prev:node1=null,var next:node1=null)
class insertAtEndDLL {
var head:node1=null

def insertEnd(data:Int)={
  val new_node=new node1(data)
  if(head==null) head=new_node
  else {
    var current=head
    while(current.next!=null) {
      current = current.next
    }
      current.next = new_node
      new_node.prev = current
    }
  }

  def displayDLL()={
    var temp=head
    while(temp!=null){
      println(temp.data)
      temp=temp.next
    }
  }
}
object insertEndDll{
  def main(args:Array[String])={
    val DLLend=new insertAtEndDLL
    DLLend.insertEnd(5)
    DLLend.insertEnd(6)
    DLLend.insertEnd(8)
    DLLend.insertEnd(7)
    DLLend.displayDLL()

  }
}
