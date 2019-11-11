package basics

object Recursion extends App {
  def factorial(n: Int): Int = if (n < 2) 1 else n * factorial(n - 1)

  def fib(n: Int): Int = if (n < 2) 1 else fib(n-2)+fib(n-1)

  def packBins(bins: Array[Double], objs: Array[Double]): Boolean = {
    def helper(o: Int): Boolean = {
      if (o >= objs.length) true
      else {
        var ret = false
        for (b <- bins.indices) {
          if (objs(o) <= bins(b)) {
            bins(b) -= objs(o)
            ret ||= helper(o + 1)   
            bins(b) += objs(o)
          }
        }
        ret
      }
    }
    helper(0)
  }

  def knapsack(values: List[Double], weights: List[Double], weightLeft: Double): Double = {
    if (values.isEmpty) 0.0
    else {
      if (weights.head <= weightLeft) {
        knapsack(values.tail, weights.tail, weightLeft) max
          values.head + knapsack(values.tail, weights.tail, weightLeft - weights.head)
      } else {
        knapsack(values.tail, weights.tail, weightLeft)
      }
    }
  }

  def sumList(lst: List[Int]): Int = lst match {
    case Nil => 0
    case h :: t => h + sumList(t)
  }
}