package adt

class UnsortedArrayPQ[A](higherP: (A, A) => Boolean) extends MyPriorityQueue[A] {
  def enqueue(value: A): Unit = ???
  def dequeue(): A = ???
  def peek(): A = ???
  def isEmpty: Boolean = ???
}