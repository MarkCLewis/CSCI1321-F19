package adt
import scala.reflect.ClassTag

class BinaryHeapPQ[A : ClassTag](higherP: (A, A) => Boolean) extends MyPriorityQueue[A] {
  private var heap = Array.fill(10)(null.asInstanceOf[A]) // new Array[A](10)
  private var back = 1

  def dequeue(): A = {
    val ret = heap(1)
    back -= 1
    var stone = 1
    var flag = true
    while (stone * 2 < back && flag) {
      var betterChild = stone * 2
      if (stone * 2 + 1 < back && higherP(heap(stone * 2 + 1), heap(betterChild))) betterChild += 1
      if (higherP(heap(betterChild), heap(back))) {
        heap(stone) = heap(betterChild)
        stone = betterChild
      } else flag = false
    }
    heap(stone) = heap(back)
    ret
  }

  def enqueue(value: A): Unit = {
    if (back >= heap.length) {
      heap = Array.tabulate(heap.length * 2)(i => if (i < heap.length) heap(i) else null.asInstanceOf[A])
    }
    var bubble = back
    while (bubble > 1 && higherP(value, heap(bubble / 2))) {
      heap(bubble) = heap(bubble / 2)
      bubble /= 2
    }
    heap(bubble) = value
    back += 1
  }

  def peek(): A = heap(1)

  def isEmpty: Boolean = back == 1
}