package adt

class DoublyLinkedList[A] extends collection.mutable.Buffer[A] {
  import DoublyLinkedList._
  // This is the sentinel
  private val end: Node[A] = new Node[A](null, null.asInstanceOf[A], null)
  end.prev = end
  end.next = end
  private var numElems = 0

  def length: Int = numElems

  def +=(a: A) = {
    val n = new Node[A](end.prev, a, end)
    end.prev.next = n
    end.prev = n
    numElems += 1
    this
  }

  def +=:(elem: A) = {
    val n = new Node[A](end, elem, end.next)
    end.next.prev = n
    end.next = n
    numElems += 1
    this
  }

  def apply(index: Int): A = {
    require(index >= 0 && index < numElems)
    var rover = end.next
    for (i <- 0 until index) rover = rover.next
    rover.data
  }

  def update(index: Int, a: A): Unit = {
    require(index >= 0 && index < numElems)
    var rover = end.next
    for (i <- 0 until index) rover = rover.next
    rover.data = a
  }

  def clear(): Unit = {
    end.next = end
    end.prev = end
    numElems = 0
  }

  def insertAll(n: Int,elems: Traversable[A]): Unit = ???

  def remove(index: Int): A = {
    require(index >= 0 && index < numElems)
    numElems -= 1
    var rover = end.next
    for (i <- 0 until index) rover = rover.next
    val ret = rover.data
    rover.next.prev = rover.prev
    rover.prev.next = rover.next
    ret
  }
  
  def iterator = new Iterator[A] {
    private var rover = end.next
    def hasNext: Boolean = rover != end
    def next(): A = {
      val ret = rover.data
      rover = rover.next
      ret
    }
  }
}

object DoublyLinkedList {
  private class Node[A](var prev: Node[A], var data: A, var next: Node[A])

  val lst = new SinglyLinkedList[Int]
  lst += 1 += 2 += 3 += 4
  val iter = lst.iterator
  while(iter.hasNext) {
    println(iter.next())
  }
}