package com.hlb

object Demo4 {
  def main(args: Array[String]): Unit = {
    // 柯里化
    // 普通函数
    def sum(x:Int,y:Int) = x + y
    // 柯里化函数
    def sum1(x:Int) = (y:Int) => x + y
    def sum2(x:Int)(y:Int) = x + y

    // 测试
    println(sum(1,2))
    println(sum1(1)(2))
    println(sum2(1)(2))
  }
}
