package scalaPractice
class node1(var data:Int,var next:node1)
class linkedListAllOperations {
var head:node1=null

  def insertFront(new_data:Int)={
    val new_node=new node1(new_data,head)
    head=new_node
  }

  def pushBetween(prev_node:node1,new_data:Int)= {
    if (prev_node.next == null) {
      println("previous_node cannot be null")
    }
    else {
      val new_node = new node1(new_data, prev_node.next)
      prev_node.next = new_node

    }
  }
    def insertEnd(new_data:Int)={
      val new_node=new node1(new_data,null)
      if(this.head==null){
        this.head=new_node
      }
      else {
        var last:node1=head
        while(last.next!=null){
          last=last.next
          last.next=new_node
      }
      }
    }
  def printList():Unit={
    var temp=head
    while(temp!=null){
      println(temp.data+" ")
      temp=temp.next
    }
  }
}

object allOps {
  def main(args: Array[String]) = {
    val liList = new linkedListAllOperations
    liList.insertFront(8)
    liList.insertFront(6)
    liList.insertFront(7)
    liList.insertEnd(3)
    liList.insertFront(4)
    liList.pushBetween(liList.head.next,9)
    liList.printList()

  }
}
