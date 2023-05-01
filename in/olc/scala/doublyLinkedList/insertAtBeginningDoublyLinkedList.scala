package in.olc.scala.doublyLinkedList

class node(var data:Int,var next:node=null,var prev:node=null)
class insertAtBeginningDoublyLinkedList {
var head:node=null
//  var prev:node=null
//  var next:node=null

  def insertFirst(data:Int)={
    val new_node=new node(data)
      if(head==null){
        head=new_node
      }
     else{
        new_node.next=head
        head=new_node
        head.prev=null
      }
  }
  def displayDLL()={
    var temp=head
    while(temp!=null){
      print(temp.data+"->")
      temp=temp.next
    }
  }
}
object DLL{
  def main(args:Array[String])={
    val dlList=new insertAtBeginningDoublyLinkedList
    dlList.insertFirst(5)
    dlList.insertFirst(6)
    dlList.insertFirst(7)
    dlList.insertFirst(8)
    dlList.displayDLL()
  }
}
