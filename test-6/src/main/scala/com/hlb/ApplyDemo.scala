package com.hlb

// object 当中的apply方法
object ApplyDemo {
  // 定义自己的apply方法
  def apply(name:String): ApplyDemo = {
    println("--------Apply in object------------")
    new ApplyDemo("Jerry")
  }

  // 如果没有apply方法,创建对象就会报错
  def main(args: Array[String]): Unit = {
    // 使用new关键字创建对象时，会调用类的构造函数来创建对象
    val a1 = new ApplyDemo("Tom")
    println("a1：" + a1.name)

    // 省略了new关键字，会在Object找对应的apply方法
    val a2 = ApplyDemo("Tom")
    println("a2: " + a2.name)
  }
}

class ApplyDemo(val name:String){ }
