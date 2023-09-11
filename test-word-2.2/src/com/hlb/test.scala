package com.hlb

/**
 * 第2小节主要介绍 面向对象编程基础
 * 具体包含2.1 --> 类
 *          2.1.1 --> 简单的类
 *          2.1.2 --> 给类增加字段和方法
 *          2.1.3 --> 创建对象
 *          2.1.4 --> 编译和执行
 *          2.1.5 --> getter和setter方法
 *          2.1.6 --> 辅助构造器
 *          2.1.7 --> 主构造器
 *        2.2 --> 对象
 *          2.2.1 --> 单例对象
 *          2.2.2 --> 伴生对象
 *          2.2.3 --> 应用程序对象
 *          2.2.4 --> apply方法和update方法
 *        2.3 --> 继承
 *          2.3.1 --> Scala 和 Java 在继承方面的区别
 *          2.3.2 --> 抽象类
 *          2.3.3 --> 扩展类
 *        2.4 --> 特质
 *          2.4.1 --> 特质概述
 *          2.4.2 --> 特质的定义
 *          2.4.3 --> 把特质混入类中
 *          2.4.4 --> 特质可以包含具体实现
 *          2.4.5 --> 把多个特质混入类中
 *        2.5 --> 模式匹配
 *          2.5.1 --> 简单匹配
 *          2.5.2 --> 类型模式
 *          2.5.3 --> "守卫（guard）"语句
 *          2.5.4 --> for表达式中的模式
 *          2.5.5 --> case类的匹配
 *          2.5.6 --> Option类型
 * */

object test {
//  def main(args: Array[String]): Unit = {
//    // 2.2.1 --> 单例对象  &nbsp&nbsp
//    /*
//      Scala并没有提供Java那样的静态方法或静态字段，但是，可以采用
//      object关键字实现单例对象，具备和Java静态方法同样的功能。
//  */
//    // 和类的定义很相似，只是关键字不一样
//    object Read {
//      private val age: Int = 0
//
//      def info(): Unit = {
//        println("my age is: " + age)
//      }
//    }
//    Read.info();
//  }
//}

  //  // 2.2.2 --> 伴生对象
  //  /*
  //  ● 在Java中,我们经常需要用到同时包含实例方法和静态方法的类,
  //  在Scala中可以通过伴生对象来实现。
  //  ● 当单例对象与某个类具有相同的名称时，它被称为这个
  //  类的“伴生对象”。
  //  ● 类和它的伴生对象必须存在于同一个文件中，而且可以
  //  相互访问私有成员(字段和方法)
  //*/
  //  // 接上面最开始的object test() 作为伴生对象
  //  private var currentAge: Int = 0
  //  private def newAge(int: Int): Int = {
  //    currentAge += int
  //    currentAge
  //  }
  //
  //  def main(args: Array[String]): Unit = {
  //      val test1 = new test("Java")
  //      val test2 = new test("Scala")
  //      test1.info()
  //      test2.info()
  //  }
  //}
  //
  ///*
  //  Scala源代码编译后都会变成JVM字节码，实际上,在编译上面的
  //  伴生对象和半身类的源代码文件以后，在Scala里面的class
  //  和object在Java层面都会被合二为一，class里面的成
  //  员成了实例成员，object成员成了static成员
  //*/
  //// 伴生类
  //class test {
  //  // 调用了伴生对象中的方法  类似于Java中的(static)方法
  //  private val age = test.newAge(12)
  //  private var name = ""
  //
  //  def this(newname: String) {
  //    this
  //    this.name = newname
  //  }
  //
  //  def info() {
  //    printf("my id is: %s, age is: %d\n", name, age)
  //  }
  //}


  def main(args: Array[String]): Unit = {
    // 2.2.3 --> 应用程序对象


  }
}


//  def main(args: Array[String]): Unit = {
//    // 2.2.4 --> apply方法和 update方法
//  }
//}
