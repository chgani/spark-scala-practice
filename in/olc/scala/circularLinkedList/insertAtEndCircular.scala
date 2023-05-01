package in.olc.scala.circularLinkedList

class node1(var data:Int,var next:node1)
class insertAtEndCircular {
var head:node=null
  def insertEnd(new_data:Int)={
    val new_node=new node(new_data,head)
    if(head==null){
      head=new_node
      new_node.next=head
    }
    else {
      var current: node = head
      while(current.next!=head){
        current=current.next
      }
      new_node.next=head
      current.next=new_node
      current=new_node //current will be new_node now

    }
  }
  def printClist():Unit={
    var temp:node=head
    if(temp!=null){
      do{
        println(temp.data+" ")
        temp=temp.next
      } while(temp!=head)
    }
  }

}
object insertEnd{
  def main(args:Array[String])={
    val cListEnd=new insertAtEndCircular()
    cListEnd.insertEnd(6)
    cListEnd.insertEnd(5)
    cListEnd.insertEnd(4)
    cListEnd.insertEnd(3)
    cListEnd.printClist()
  }
}
