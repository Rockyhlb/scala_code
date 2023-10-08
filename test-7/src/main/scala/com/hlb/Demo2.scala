package com.hlb

import scala.math._

object Demo2 {
  def main(args: Array[String]): Unit = {
//    // 匿名函数
//    (x:Int) => x*3
//    // (1,2,3)  ==>  (3,6,9)
//    Array(1,2,3).map((x:Int) => x*3)

    // 高阶函数：带有函数参数的函数
    // 例子1
    def function3(f:(Double) => Double) = f(10)
    println(function3(sqrt))

    // 例子2
    val f = (x:Int) => x*3
    // (1,2,3)  ==>  (3,6,9)
    Array(1,2,3).map(f:(Int) => Int ).foreach(println)

    // 例子3
    def fun1(x:Int,y:Int):Int = {x*y + 100}
    def myFunction(f:(Int,Int) => Int, x:Int, y:Int) = f(x,y)
    // res = 102
    println(myFunction(fun1,1,2))
  }
}
