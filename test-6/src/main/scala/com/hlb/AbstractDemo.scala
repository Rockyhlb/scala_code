package com.hlb

// scala当中的抽象类
// 父类：抽象类
abstract class action{
  // 定义抽象方法
  def eat():String
  // scala当中的抽象字段，就是没有初始值的字段
  // 第一个抽象字段，只有get方法
  val name:String
  // 第二个抽象字段，有get和set方法
  var age:Int
}

// 子类
// 若父类含有抽象字段，应该提供抽象字段的初始值，否则该子类也应该是抽象的
class Pig extends action{
  override def eat(): String = "I'm Pig,and i like eat"
  override var age: Int = 12
  override val name: String = "pig"
}

class Tiger extends action{
  override def eat(): String = "I'm Tiger,and i like eat"
  override var age: Int = 10
  override val name: String = "tiger"
}

object AbstractDemo {
  // 定义两个对象
  def main(args: Array[String]): Unit = {
    val p1 = new Pig
    println(p1.name + "\t\t" + p1.age)
    println(p1.eat())
    val t1 = new Tiger
    println(t1.name + "\t" + t1.age)
    println(t1.eat())
  }
}

