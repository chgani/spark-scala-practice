package in.olc.scala.singleLinkedList

class node_2(var data:Int,var next:node_2)
class nodesCount {
  var head: node_2 = null

  def insertEnd(new_data: Int) = {
    val new_node = new node_2(new_data, null)
    if (head == null) {
      head = new_node
    }
    else {
      var present: node_2 = head //we need to start from first and reach to end
      while (present.next != null) {
        present = present.next

      }
      present.next = new_node
    }
  }

  def printList(): Unit = {
    var temp = head
    while (temp != null) {
      print(temp.data + " =>")
      temp = temp.next
    }
  }
  def count():Int={
    var i:Int=0
    var current=head
    while(current!=null){
      i=i+1
      current=current.next
    }
    i
  }
}
object count {
  def main(args: Array[String]) = {
    val ctLL = new nodesCount
    ctLL.insertEnd(8)
    ctLL.insertEnd(6)
    ctLL.insertEnd(7)
    ctLL.insertEnd(3)
    ctLL.insertEnd(4)
    ctLL.printList()
    val result=ctLL.count()
    println(s"number of nodes= $result")

  }
}


