package com.hlb

object Demo1 {
  def main(args: Array[String]): Unit = {
    // Scala中的函数是“头等公民”，可以在变量中存放函数(值函数)
    def function1(name:String):String = "hello," + name
    println(function1("Tom"))
    def function2():String = "hello world"

    // 值函数：将函数作为变量的值
    val f1 = function1("Tom")
    val f2 = function2()
    // 再将f1赋给function1
    println(function1(f1))
  }
}
