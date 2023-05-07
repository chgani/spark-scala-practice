package in.olc.scala.circularLinkedList
class node_1(var data:Int,var next:node_1)
class pushBetweenNodes {
  var head:node_1=null
  def insertBegin(new_data:Int)={
    val new_node=new node_1(new_data,null)
    if(head==null){
      head=new_node
      new_node.next=head
    }
    else{
      var current=head
        while(current.next!=head){
          current=current.next
        }
      current.next=new_node
      new_node.next=head
      head=new_node
    }
  }
  def pushBwNodes(prev_node:node_1)={

  }
  def displayCLL()={
    var temp=head
    if(temp!=null){
      do{
        print(temp.data+"=>")
        temp=temp.next
      }while(temp!=head)
    }
  }
}
object pushBw{
  def main(args:Array[String]):Unit={
    val pushCLL=new pushBetweenNodes
    pushCLL.insertBegin(8)
    pushCLL.insertBegin(5)
    pushCLL.insertBegin(10)
    pushCLL.insertBegin(6)
    pushCLL.insertBegin(3)
    pushCLL.displayCLL()

  }
}
