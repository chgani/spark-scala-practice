package in.olc.scala.singleLinkedList

class node2(var data:Int,var next:node2)
class deleteNodeLinkedList {
  var head: node2 = null

  def insertFront(new_data: Int) = {
    val new_node = new node2(new_data, head)
    head = new_node
  }
  def insertEnd(new_data: Int): Unit = {
    val new_node = new node2(new_data, null)
    if (head == null) {
      head = new_node
    }
    else {
      var current: node2 = head
      while (current.next != null) {
        current = current.next
      }
      current.next = new_node
    }
  }

  def printList(): Unit = {
    var temp: node2 = head
    while (temp != null) {
      print(temp.data + "-->")
      temp = temp.next
    }
  }
  def deleteNode(pos:Int)={
    if(head==null){
      head
    }
    else {
      var temp:node2=head
      for(i<-0 until pos){

      }
    }

  }
}
object delete{
  def main(args:Array[String])={
    var lList2=new deleteNodeLinkedList

    lList2.insertEnd(5)
    lList2.insertEnd(2)
    lList2.insertEnd(6)
    lList2.insertEnd(9)
    lList2.insertEnd(1)
    lList2.insertFront(4)
    lList2.printList()
  }
}

