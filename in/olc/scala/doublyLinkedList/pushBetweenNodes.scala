package in.olc.scala.doublyLinkedList

class node2(var data:Int,var prev:node2=null,var next:node2=null)
class pushBetweenNodes {
var head:node2=null
  def insertBegin(data:Int)={
    val new_node=new node2(data)
    if(head==null) head=new_node
    else{
      new_node.next=head
      head.prev=new_node
      head=new_node
    }
  }
  def pushBetween(prev_node:node2,new_data:Int): Unit ={
    if(prev_node.next==null) println("previous node cannot be null")
    else{
      val newnode=new node2(new_data,prev_node,prev_node.next)
      prev_node.next=newnode
      newnode.next.prev=newnode
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

object pushBetween{
  def main(args:Array[String])={
    val DLL=new pushBetweenNodes
    DLL.insertBegin(4)
    DLL.insertBegin(8)
    DLL.insertBegin(9)
    DLL.insertBegin(1)
    DLL.pushBetween(DLL.head.next.next,10)
    DLL.displayDLL()
  }
}
