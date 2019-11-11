package adt
import scala.collection.BitSet

object Graphs extends App {
  val g = Array(
    Array(0,1,1,0,0,0),
    Array(0,0,1,0,0,0),
    Array(0,0,0,1,1,0),
    Array(1,0,0,0,0,0),
    Array(0,1,0,1,1,0),
    Array(1,0,1,0,0,0)
  )

  // Nodes in your MUD would be passed around as Strings for the keywords.
  def reachable(node1: Int, node2: Int, connect: Array[Array[Int]]): Boolean = {
    def helper(n1: Int, visited: Set[Int]): Boolean = {
      if (n1 == node2) true else {
        var ret = false
        val newVisited = visited + n1
        for (n <- 0 until connect.length) {
          if (connect(n1)(n) != 0 && !visited(n)) {
            ret ||= helper(n, newVisited)
          }
        }
        ret
      }
    }
    helper(node1, Set.empty)
  }

  def shortestPath(node1: Int, node2: Int, connect: Array[Array[Int]]): Int = {
    def helper(n1: Int, visited: Set[Int]): Int = {
      if (n1 == node2) 0 else {
        var ret = 1000000000
        val newVisited = visited + n1
        for (n <- 0 until connect.length) {
          if (connect(n1)(n) != 0 && !visited(n)) {
            ret = ret min helper(n, newVisited)
          }
        }
        ret + 1
      }
    }
    helper(node1, Set.empty)
  }
  
  println(reachable(0, 4, g))
  println(reachable(0, 5, g))
  println(shortestPath(0, 4, g))
  println(shortestPath(0, 5, g))
  println(shortestPath(5, 0, g))
}