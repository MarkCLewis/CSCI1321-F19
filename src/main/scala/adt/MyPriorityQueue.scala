package adt

trait MyPriorityQueue[A] {
  def enqueue(value: A): Unit
  def dequeue(): A
  def peek(): A
  def isEmpty: Boolean
}