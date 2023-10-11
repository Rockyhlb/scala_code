package com.hlb

import scala.reflect.ClassTag

object Demo2 {
  def main(args: Array[String]): Unit = {

    // 创建一个函数，可以创建一个Int类型的数组
    def mkIntArray(elems:Int*) = Array[Int](elems:_*)
    println("========================")
    mkIntArray(1,2,3,4,5).foreach(println)

    // 创建一个函数，可以创建一个String类型的数组
    def mkStringArray(elems:String*) = Array[String](elems:_*)
    println("========================")
    mkStringArray("Scala","Java","Python").foreach(println)

    // 创建一个函数，可以创建Int类型的数组，也是String类型的数组
    // 这里的ClaassTag是必须的，表示运行时的信息，比如类型
    def mkArray[T:ClassTag](elems:T*) = Array[T](elems:_*)
    println("========================")
    for (elem <- mkArray(1, 2, 3)) {println(elem)}
    println("========================")
    mkArray("scala","java").foreach(println)
  }
}
