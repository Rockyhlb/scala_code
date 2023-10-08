package com.hlb

object Demo3 {
  def main(args: Array[String]): Unit = {
    // 闭包:
    // 就是函数的嵌套,在一个函数定义中，包含另外一个函数的定义，并且在内函数中可以访问外函数中的变量
    def mulBy(f:Double) = (x:Double) => x*f
    // 翻倍  f=2
    val triple = mulBy(2)
    // 除半   f=0.5
    val half = mulBy(0.5)
    // x=2, f=2, res=4
    println("After triple: " + triple(2))
    println("After half: " + half(4))
  }
}
