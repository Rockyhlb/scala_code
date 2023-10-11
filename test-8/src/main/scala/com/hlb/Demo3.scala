package com.hlb

object Demo3 {
  def main(args: Array[String]): Unit = {
    // 隐式转换函数：以implicit关键字声明的带有单个参数的函数
    implicit def intToString(n:Int):String = { n.toString }
    // 当我们将整数42赋值给一个字符串类型的变量时，隐式转换函数会自动被调用，将整数转换为字符串。
    // 若没有隐式转换参数，此处编译器会因为无法判断23的类型而报错
    val str:String = 23
    println("str after intToString is: " + str.getClass)

    // 隐式参数：使用implicit声明的函数参数，允许在函数调用时自动传递参数，而无需显式地提供参数值
    // 编译器会在需要时查找合适的隐式值并自动传递给函数。
    // Scala隐式参数
    def greet(name: String)(implicit greeting: String): Unit = {
      println(greeting + ", " + name)
    }
    // 定义一个隐式值
    implicit val defaultGreeting: String = "Hello"
    // 测试程序：输出“Hello,Alice”
    greet("Alice")
    // 在上面的示例中，我们定义了一个带有隐式参数的函数greet，它接受一个字符串参数name和一个隐式参数greeting。
    // 我们还定义了一个隐式值defaultGreeting，它作为默认的问候语。
    // 当我们调用greet("Alice")时，编译器会自动查找合适的隐式值，并将其传递给函数。
    // 在这种情况下，编译器会找到defaultGreeting作为隐式值，并将其传递给greet函数，输出"Hello, Alice!"。

    def smaller[T](a:T,b:T)(implicit order:T => Ordered[T]) = if (a < b) a else b
    println(smaller(1,2))
    println(smaller("hello","java"))
  }
}