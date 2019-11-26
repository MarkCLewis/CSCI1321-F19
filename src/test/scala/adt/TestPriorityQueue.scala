package adt

import org.junit.Test
import org.junit.Assert._
import org.junit.Before

class TestPriorityQueue {
  private var pq: MyPriorityQueue[Int] = null

  @Before
  def makePQ(): Unit = {
    pq = new BinaryHeapPQ[Int](_ < _)
  }

  @Test
  def emptyOnCreate(): Unit = {
    assertTrue(pq.isEmpty)
  }

  @Test
  def addRemoveThree(): Unit = {
    pq.enqueue(8)
    pq.enqueue(2)
    pq.enqueue(4)
    assertEquals(2, pq.peek())
    assertEquals(2, pq.dequeue())
    assertEquals(4, pq.peek())
    assertEquals(4, pq.dequeue())
    assertEquals(8, pq.peek())
    assertEquals(8, pq.dequeue())
  }

  @Test
  def bigTest(): Unit = {
    val nums = Array.fill(10000000)(util.Random.nextInt)
    for (n <- nums) pq.enqueue(n)
    for (n <- nums.sorted) {
      assertEquals(n, pq.peek())
      assertEquals(n, pq.dequeue())
    }
  }
}