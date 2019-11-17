package adt

import collection.mutable

class BSTMap[K, V](lt: (K, K) => Boolean) extends mutable.Map[K, V] {
  import BSTMap._

  private var root: Node[K, V] = null

  def get(key: K): Option[V] = {
    var rover = root
    while (rover != null && rover.key != key) {
      if (lt(key, rover.key)) rover = rover.left else rover = rover.right
    }
    if (rover != null) Some(rover.value) else None
  }

  def iterator = new Iterator[(K, V)] {
    val stack = mutable.Stack[Node[K, V]]()
    def pushAllLeft(n: Node[K, V]): Unit = {
      if (n != null) {
        stack.push(n)
        pushAllLeft(n.left)
      }
    }
    pushAllLeft(root)

    def hasNext: Boolean = stack.nonEmpty
    def next(): (K, V) = {
      val ret = stack.pop()
      pushAllLeft(ret.right)
      (ret.key, ret.value)
    }
  }

  override def update(key: K, value: V): Unit = {
    var rover = root
    while (rover != null && rover.key != key) {
      if (lt(key, rover.key)) rover = rover.left else rover = rover.right
    }
    if (rover != null) rover.value = value
  }
  
  def -=(key: K) = {
    // find it
    // check special cases, 0 or 1 children
    // otherwise replace with smallest on right
    ???
  }

  def +=(kv: (K, V)) = {
    def helper(n: Node[K, V]): Node[K, V] = {
      if (n == null) { 
        new Node[K, V](kv._1, kv._2, null, null)
      } else {
        if (n.key == kv._1) {
          n.value = kv._2
        } else if (lt(n.key, kv._1)) {
          n.left = helper(n.left)
        } else {
          n.right = helper(n.right)
        }
        n
      }
    }
    root = helper(root)
    this
  }

  def preorder(visitor: (K, V) => Unit): Unit = {
    def helper(n: Node[K, V]): Unit = {
      if (n != null) {
        visitor(n.key, n.value)
        helper(n.left)
        helper(n.right)
      }
    }
    helper(root)
  }

  def postorder(visitor: (K, V) => Unit): Unit = {
    def helper(n: Node[K, V]): Unit = {
      if (n != null) {
        helper(n.left)
        helper(n.right)
        visitor(n.key, n.value)
      }
    }
    helper(root)
  }

  def inorder(visitor: (K, V) => Unit): Unit = {
    def helper(n: Node[K, V]): Unit = {
      if (n != null) {
        helper(n.left)
        visitor(n.key, n.value)
        helper(n.right)
      }
    }
    helper(root)
  }
}

object BSTMap {
  private class Node[K, V](val key: K, var value: V, var left: Node[K, V], var right: Node[K, V])
}