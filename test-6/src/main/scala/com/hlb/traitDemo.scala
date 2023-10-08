package com.hlb

/**
 * scala 当中的trait
 * 1. trait就是抽象类
 * 2. 区别：trait支持多重继承
 */

// 第一个特质
trait Person1{
  val id:Int
  val name:String
  def greet():String = "hello," + name
}
// 第二个特质
trait Action1{
  def getActionName:String
}

// 子类
class Student(override val id:Int,override val name:String) extends Person1 with Action1{
  override def getActionName: String = "Action of student is running"
}

object traitDemo {
  def main(args: Array[String]): Unit = {
    // 创建学生对象
    val s1 = new Student(100,"Tom")
    println(s1.greet())
    println(s1.getActionName)
  }
}
