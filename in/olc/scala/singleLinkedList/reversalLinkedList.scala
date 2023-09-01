package in.olc.scala.singleLinkedList

class node_e(var data:Int,var next:node_e)
class reversalLinkedList {
  var head: node_e = null

  def insertEnd(new_data: Int) = {
    val new_node = new node_e(new_data, null)
    if (head == null) {
      head = new_node
    }
    else {
      var present: node_e = head
      while (present.next != null) {
        present = present.next
      }
      present.next = new_node
    }
  }

  def display(): Unit = {
    var temp = head
    while (temp!= null) {
      print(temp.data + "-->")
      temp = temp.next
    }
  }
  def reverse():Unit={
    var prev:node_e=null
    var next:node_e=null
    var current=head //initially current=100
    while(current!=null){ //head=100 ->10,200 (address=100) ->20,400(address=200) ->30,null(address=400) ->null
      next=current.next //200;400
      current.next=prev //null;100
      prev=current //null;200
      current=next //200;400
    }
    head=prev //30,200 (address=400)
  }
}
  object reversal{
    def main(args:Array[String]):Unit={
      val revLL=new reversalLinkedList
      revLL.insertEnd(5)
      revLL.insertEnd(7)
      revLL.insertEnd(6)
      revLL.insertEnd(8)
      revLL.display()
      println("\nreversal")
      revLL.reverse()
      revLL.display()
    }
  }


