package com.hlb

// Scala中的包对象：常量，变量，方法，类，对象，特质(trait)

// 定义一个包对象
package object MyPackageObject{
  // 常量
  val value = 12
  // 变量
  var str:String = "hello"
  // 方法
  def greet(): String = {"hello world"}
  // 类
  class PackageClassTest{
    def hello(): Unit ={
      println("hello world")
    }
  }
  // 对象
  object PackageObjectTest{}
  // 特质(trait)
  trait testTrait{}
}

object packageDemo {
  // 测试程序
  def main(args: Array[String]): Unit = {
    // 导入需要的包对象
    import com.hlb.MyPackageObject._
    // 定义包MyPackageObject中的PackageClassTest对象
    val c1 = new PackageClassTest
    c1.hello()
  }
}
