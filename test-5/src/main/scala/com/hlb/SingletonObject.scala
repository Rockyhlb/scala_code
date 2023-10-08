package com.hlb

// 伴生类可以和伴生对象互相访问其私有成员
// 伴生类
class Singleton {
  // 声明idCard属性只属于该对象,是静态的
  private[this] var idCard:Long = 20221008

  private val date:String = "2022-10-08"

  def newIdCard():Long = {
    idCard+=1
    idCard
  }
}

// 伴生对象
object Singleton {
  // 测试程序
  def main(args: Array[String]): Unit = {
    val singleton = new Singleton
    val teacher = new Teacher
    println(singleton.newIdCard())
    println(singleton.newIdCard())
    println(singleton.newIdCard())
    println(singleton.newIdCard())
    // 可以访问伴生类的私有成员
    println(singleton.date)
    // 不是伴生类,不能访问私有成员
//    println(teacher.salary)
  }
}

class Teacher {
  private var salary:Int = 7000
}
