package adt

import org.junit.Test
import org.junit.Assert._
import org.junit.Before

class TestBSTMap {
  var tree: BSTMap[String, Int] = null
  @Before def before(): Unit = {
    tree = new BSTMap[String, Int](_ < _)
  }

  @Test def startEmpty(): Unit = {
    assertTrue(tree.isEmpty)
  }

  @Test def with3(): Unit = {
    tree += ("b" -> 2) += ("a" -> 1) += ("c" -> 3)
    for ((i, (s, j)) <- List(1, 2, 3).zip(tree)) assertEquals(i, j)
  }

  @Test def with9AndRemove(): Unit = {
    tree += ("e" -> 5) += ("b" -> 2) += ("a" -> 1) += ("c" -> 3) += ("d" -> 4) += ("h" -> 8) += ("i" -> 9) += ("g" -> 7) += ("f" -> 6)
    for ((i, (s, j)) <- (1 to 9).zip(tree)) assertEquals(i, j)
    tree -= "e"
    for ((i, (s, j)) <- (1 to 9).diff(List(5)).zip(tree)) assertEquals(i, j)
  }

  @Test def bigRandom(): Unit = {
    val data = Array.fill(100)((1 to 10).map(_ => ('a' + util.Random.nextInt(26)).toChar).mkString -> util.Random.nextInt())
    for (t <- data) tree += t
    var sorted = data.sortBy(_._1)
    for (((s1, i1), (s2, i2)) <- sorted.zip(tree)) {
      assertEquals(s1, s2)
      assertEquals(i1, i2)
    }
    while (tree.nonEmpty) {
      val r = util.Random.nextInt(sorted.length)
      val (s, i) = sorted(r)
      tree -= s
      sorted = sorted.patch(r, Nil, 1)
      for (((s1, i1), (s2, i2)) <- sorted.zip(tree)) {
        assertEquals(s1, s2)
        assertEquals(i1, i2)
      }
    }
  }
}