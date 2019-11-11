package adt

import org.junit.Test
import org.junit.Assert._
import org.junit.Before

class TestPriorityQueue {
  private var pq: MyPriorityQueue[Int] = null

  @Before
  def makePQ(): Unit = {
    pq = new UnsortedArrayPQ[Int](_ < _)
  }
}