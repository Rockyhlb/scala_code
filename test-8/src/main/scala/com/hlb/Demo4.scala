package com.hlb

object Demo4 {

  // 隐式类:隐式类是一种用于扩展现有类的特殊类。它们允许在不修改原始类的情况下，为该类添加新的方法和功能。
  // 隐式类必须定义在一个对象、类或者包对象中，并且只能有一个参数的主构造函数。
  implicit class Calc(x:Int){
    def add(num:Int):Int = num * x
  }

  // 测试程序
  def main(args: Array[String]): Unit = {
    println("sum is : " + 1.add(21))
  }

  /**
   * 隐式类的执行过程：
   * 1、当1.add(21)，scala的编译器不会立即报错，
   * 首先会先在当前域中查询有没有implicit修饰的同时可以将Int作为参数的构造器，
   * 然后再找具有add方法的类，
   * 最终通过查询得到Calc方法
   * 2、利用隐式类Calc来执行add方法
   */

  /*
  隐式转换函数、隐式参数和隐式类是Scala中非常强大和灵活的特性，
  它们可以帮助我们实现更简洁、可扩展和易用的代码。
  然而，过度使用隐式特性可能会导致代码难以理解和维护，因此需要谨慎使用。
   */

}
