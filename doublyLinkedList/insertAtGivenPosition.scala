package doublyLinkedList
class node_1(var data:Int,var prev:node_1=null,var next:node_1=null)
class insertAtGivenPosition {
  var head:node_1=null
  def insertEnd(new_data:Int)={
    val new_node=new node_1(new_data)
    if(head==null) head=new_node
    else{
      var current=head
      while(current.next!=null){
        current=current.next
      }
      current.next=new_node
      new_node.prev=current

    }
  }
  def insertAtPosition(new_data:Int,position:Int) ={
    val new_node=new node_1(new_data)
    if(position<1) println("position should be greater than or equal to 1")
    else if (position==1){
      new_node.next=head
      head.prev=new_node
      head=new_node
    }
    else {
      var temp=head
      for(i<-1 until position) {
        if (temp != null) temp = temp.next
      }
        if(temp!=null){
          new_node.next=temp.next
          new_node.prev=temp
          temp.next=new_node
          if(new_node.next!=null) new_node.next.prev=new_node
      }
        else println("the previous node is null")


    }
  }
  def displayDLL()={
    var temp=head
    while(temp!=null){
      print(temp.data+"=>")
      temp=temp.next
    }
  }

}
object insertNode{
  def main(args:Array[String])={
    val dllGP=new insertAtGivenPosition
    dllGP.insertEnd(10)
    dllGP.insertEnd(5)
    dllGP.insertEnd(11)
    dllGP.insertEnd(17)
    dllGP.insertAtPosition(18,1)
    dllGP.displayDLL()

  }
}
