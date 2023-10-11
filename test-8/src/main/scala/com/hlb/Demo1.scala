package com.hlb

/**
 * User:麦田里的乌鸦
 * Date:2023-10-11
 * Time:18:14
 */

// 泛型类
class Demo1[T]{
  // 定义一个变量
  private var content:T = _
  // set 方法
  def set(value:T): Unit ={
    content = value
  }
  // get方法
  def get():T ={
    content
  }
}

object Demo1 {
  // 测试程序
  def main(args: Array[String]): Unit = {
    // 生成一个整数类型的泛型对象
    var demo1 = new Demo1[Int]
    demo1.set(100)
    println("demo1.value = " + demo1.get())

    // 生成一个Sting类型的泛型对象
    var demo2 = new Demo1[String]
    demo2.set("hello")
    println("demo2.value = " + demo2.get())

  }
}

