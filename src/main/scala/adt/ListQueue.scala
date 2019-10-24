package adt

class ListQueue[A] extends MyQueue[A] {
  import ListQueue._
  private var front: Node[A] = null
  private var back: Node[A] = null

  def enqueue(value: A): Unit = {
    back.next = new Node[A](value, null)
    back = back.next
  }
  def dequeue(): A = {
    val ret = front.data
    front = front.next
    if (front == null) back = null
    ret
  }
  def peek(): A = front.data
  def isEmpty: Boolean = front == null
}

object ListQueue {
  private class Node[A](val data: A, var next: Node[A])
}