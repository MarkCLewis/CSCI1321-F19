package adt

class SinglyLinkedList[A] extends collection.mutable.Buffer[A] {
  import SinglyLinkedList._
  private var hd: Node[A] = null
  private var tl: Node[A] = null
  private var numElems = 0

  def length: Int = numElems

  def +=(a: A) = {
    val n = new Node[A](a, null)
    if (tl == null) {
      hd = n
      tl = n
    } else {
      tl.next = n
      tl = n
    }
    numElems += 1
    this
  }

  def +=:(elem: A) = {
    hd = new Node[A](elem, hd)
    if (tl == null) tl = hd
    numElems += 1
    this
  }

  def apply(index: Int): A = {
    require(index >= 0 && index < numElems)
    var rover = hd
    for (i <- 0 until index) rover = rover.next
    rover.data
  }

  def update(index: Int, a: A): Unit = {
    require(index >= 0 && index < numElems)
    var rover = hd
    for (i <- 0 until index) rover = rover.next
    rover.data = a
  }

  def clear(): Unit = {
    hd = null
    tl = null
    numElems = 0
  }

  def insertAll(n: Int,elems: Traversable[A]): Unit = ???

  def remove(index: Int): A = {
    require(index >= 0 && index < numElems)
    numElems -= 1
    if (index == 0) {
      val ret = hd.data
      hd = hd.next
      if (hd == null) tl = null
      ret
    } else {
      var rover = hd
      for (i <- 0 until index - 1) rover = rover.next
      val ret = rover.next.data
      rover.next = rover.next.next
      if (rover.next == null) tl = rover
      ret
    }
  }
  
  def iterator = new Iterator[A] {
    private var rover = hd
    def hasNext: Boolean = rover != null
    def next(): A = {
      val ret = rover.data
      rover = rover.next
      ret
    }
  }
}

object SinglyLinkedList {
  private class Node[A](var data: A, var next: Node[A])

  val lst = new SinglyLinkedList[Int]
  lst += 1 += 2 += 3 += 4
  val iter = lst.iterator
  while(iter.hasNext) {
    println(iter.next())
  }
}