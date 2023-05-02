package in.olc.scala.doublyLinkedList

class node_2(var data:Int,var prev:node_2=null,var next:node_2=null)
class deleteNodeDLL {
  var head:node_2=null
  def insertNode(data:Int)= {
    val new_node = new node_2(data)
    if (head == null) head = new_node
    else {
      var current = head
      while (current.next != null) {
        current = current.next
      }
      new_node.prev = current
      current.next = new_node
    }
  }
  def deleteBegin()={
    if(head==null) println("No nodes to delete")
    else {
      head.next.prev=null
      head=head.next
    }
  }
  def deleteEnd(): Unit ={
    if(head==null) println("No nodes to delete")
    else{
      var current=head
      while(current.next!=null){
        current=current.next
      }
      current.prev.next=null
    }
  }
def deleteNodeBwNodes(prev_node:node_2)={
  if(prev_node.next==null) println("previous node cannot be null")
  else {
    prev_node.next.prev=prev_node
    prev_node.next=prev_node.next.next
  }
}
  def deleteNodeAtGivenPosition(position:Int)={

  }
  def displayNodes()={
    var temp=head
    while(temp!=null){
      print(temp.data+"->")
      temp=temp.next
    }
  }
}

object deleteNode{
  def main(args:Array[String])={
    val deNode=new deleteNodeDLL
    deNode.insertNode(5)
    deNode.insertNode(7)
    deNode.insertNode(9)
    deNode.insertNode(10)
    deNode.insertNode(8)
    deNode.insertNode(15)
    deNode.insertNode(1)
    deNode.insertNode(20)
    deNode.displayNodes()
    println("\n After deleting node at begining")
    deNode.deleteBegin()
    deNode.displayNodes()
    println("\n After deleting node at end")
    deNode.deleteEnd()
    deNode.displayNodes()
    println("\n After deleting node between nodes")
    deNode.deleteNodeBwNodes(deNode.head.next.next)
    deNode.displayNodes()
  }
}
