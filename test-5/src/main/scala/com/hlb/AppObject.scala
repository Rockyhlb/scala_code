package com.hlb

object AppObject extends App {
  // 使用应用程序对象，可以省略main方法
  // 相当于 <==> def main(args: Array[String]): Unit = {println("hello world") }
  println("hello world")

  // 如何取得命令行的参数
  if(args.length > 0){
    println(args(0))
  }
  else {
    println("No Arguments")
  }
}
