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
    def main(args: Array[String]): Unit = {
      // 2.2.1 --> 单例对象  &nbsp&nbsp
      /*
        Scala并没有提供Java那样的静态方法或静态字段，但是，可以采用
        object关键字实现单例对象，具备和Java静态方法同样的功能。
    */
      // 和类的定义很相似，只是关键字不一样
      object Read {
        private val age: Int = 0

        def info(): Unit = {
          println("my age is: " + age)
        }
      }
      Read.info();
    }
  }

//                          _ooOoo_                          //\n\
//                         o8888888o                         //\n\
//                         88\" . \"88                         //\n\
//                         (| ^_^ |)                         //\n\
//                         O\\  =  /O                         //\n\
//                      ____/`---'\\____                      //\n\
//                    .'  \\\\|     |//  `.                    //\n\
//                   /  \\\\|||  :  |||//  \\                   //\n\
//                  /  _||||| -:- |||||-  \\                  //\n\
//                  |   | \\\\\\  -  /// |   |                  //\n\
//                  | \\_|  ''\\---/''  |   |                  //\n\
//                  \\  .-\\__  `-`  ___/-. /                  //\n\
//                ___`. .'  /--.--\\  `. . ___                //\n\
//               ."" '<  `.___\\_<|>_/___.'  >'"".                //\n\
//            | | :  `- \\`.;`\\ _ /`;.`/ - ` : | |            //\n\
//            \\  \\ `-.   \\_ __\\ /__ _/   .-` /  /            //\n\
//     ========`-.____`-.___\\_____/___.-`____.-'========     //\n\
//                          `=---='                          //\n\
//     ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^     //\n\
//           佛祖保佑       永不宕机     永无BUG             //\n\


// 2.2.2 --> 伴生对象
    /*
    ● 在Java中,我们经常需要用到同时包含实例方法和静态方法的类,
    在Scala中可以通过伴生对象来实现。
    ● 当单例对象与某个类具有相同的名称时，它被称为这个
    类的“伴生对象”。
    ● 类和它的伴生对象必须存在于同一个文件中，而且可以
    相互访问私有成员(字段和方法)
  */
    // 接上面最开始的object test() 作为伴生对象
    private var currentAge: Int = 0
    private def newAge(int: Int): Int = {
      currentAge += int
      currentAge
    }

    def main(args: Array[String]): Unit = {
        val test1 = new test("Java")
        val test2 = new test("Scala")
        test1.info()
        test2.info()
    }
  }

  /*
    Scala源代码编译后都会变成JVM字节码，实际上,在编译上面的
    伴生对象和半身类的源代码文件以后，在Scala里面的class
    和object在Java层面都会被合二为一，class里面的成
    员成了实例成员，object成员成了static成员
  */
  // 伴生类
  class test {
    // 调用了伴生对象中的方法  类似于Java中的(static)方法
    private val age = test.newAge(12)
    private var name = ""

    def this(newname: String) {
      this
      this.name = newname
    }

    def info() {
      printf("my id is: %s, age is: %d\n", name, age)
    }
  }

  //                          _ooOoo_                          //\n\
  //                         o8888888o                         //\n\
  //                         88\" . \"88                         //\n\
  //                         (| ^_^ |)                         //\n\
  //                         O\\  =  /O                         //\n\
  //                      ____/`---'\\____                      //\n\
  //                    .'  \\\\|     |//  `.                    //\n\
  //                   /  \\\\|||  :  |||//  \\                   //\n\
  //                  /  _||||| -:- |||||-  \\                  //\n\
  //                  |   | \\\\\\  -  /// |   |                  //\n\
  //                  | \\_|  ''\\---/''  |   |                  //\n\
  //                  \\  .-\\__  `-`  ___/-. /                  //\n\
  //                ___`. .'  /--.--\\  `. . ___                //\n\
  //               ."" '<  `.___\\_<|>_/___.'  >'"".                //\n\
  //            | | :  `- \\`.;`\\ _ /`;.`/ - ` : | |            //\n\
  //            \\  \\ `-.   \\_ __\\ /__ _/   .-` /  /            //\n\
  //     ========`-.____`-.___\\_____/___.-`____.-'========     //\n\
  //                          `=---='                          //\n\
  //     ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^     //\n\
  //           佛祖保佑       永不宕机     永无BUG             //\n\

    def main(args: Array[String]): Unit = {
      // 2.2.3 --> 应用程序对象
  /*
      每个Scala应用程序都必须从一个对象的main方法开始
      为了运行代码，我们在Linux shell中有两种不同的办法
      一、直接用scala命令运行得到结果
        scala test.scala
      二、先编译再执行
        scalac test-9.11.scala
        scala -classpath . test(object name) // 执行时使用的是对象名称
  */
    }
  }

  //                          _ooOoo_                          //\n\
  //                         o8888888o                         //\n\
  //                         88\" . \"88                         //\n\
  //                         (| ^_^ |)                         //\n\
  //                         O\\  =  /O                         //\n\
  //                      ____/`---'\\____                      //\n\
  //                    .'  \\\\|     |//  `.                    //\n\
  //                   /  \\\\|||  :  |||//  \\                   //\n\
  //                  /  _||||| -:- |||||-  \\                  //\n\
  //                  |   | \\\\\\  -  /// |   |                  //\n\
  //                  | \\_|  ''\\---/''  |   |                  //\n\
  //                  \\  .-\\__  `-`  ___/-. /                  //\n\
  //                ___`. .'  /--.--\\  `. . ___                //\n\
  //               ."" '<  `.___\\_<|>_/___.'  >'"".                //\n\
  //            | | :  `- \\`.;`\\ _ /`;.`/ - ` : | |            //\n\
  //            \\  \\ `-.   \\_ __\\ /__ _/   .-` /  /            //\n\
  //     ========`-.____`-.___\\_____/___.-`____.-'========     //\n\
  //                          `=---='                          //\n\
  //     ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^     //\n\
  //           佛祖保佑       永不宕机     永无BUG             //\n\

  def main(args: Array[String]): Unit = {
    // 2.2.4 --> apply方法和 update方法
/*
    我们经常会用到对象的apply方法和update方法,虽然我们表面上并没
    有察觉，但是，实际上，在Scala中， apply方法和update方法都会遵循相关的约定被调用，约定如下:
    ● 用括号传递给变量(对象)-一个或多个参数时，Scala会把它转换成对
    apply方法的调用
    ● 当对带有括号并包括一到若干参数的对象进行赋值时，编译器将调用
    对象的update方法，在调用时，是把括号里的参数和等号右边的对象
    一起作为update方法的输入参数来执行调用
*/
      class testApply {
        def apply(string: String): String = {
          println("apply method is called,str is: " + string)
          "hello scala"
        }
      }
      val testApply1 = new testApply()
      println(testApply1("Java"))

      // 上面是类中定义apply方法，下面试下在单例对象中定义apply方法
      object testApply {
        def apply(string: String): String = {
          println("apply method is called")
          string
        }
      }
      val testApply2 = testApply("Scala")
      println(testApply2)

      // 下面我们测试一个半生类和伴生对象中的apply方法的实例
      // 先调用伴生对象中的apply方法
      val testApply3 = testApply()
      testApply3.greetingOfClass
      testApply3()
    }
    // 伴生类
    class testApply {
      def apply() = {
        println("Apply method in class is called")
      }
      def greetingOfClass = {
        println("Greeting method is called in class")
      }
    }

      // 伴生对象
    object testApply {
      def apply() = {
        println("Apply method in object is called")
        new testApply()
      }
    }

    /*
    由于Scala中的Array对象定义了apply方法
    也就是说，不需要new关键字，不用构造器，直接给对象传递3个参
    数，Scala就会转换成对apply方法的调用，也就是调用Array类的伴
    生对象Array的apply方法，完成数组的初始化。
*/
    val myStrArr = Array("BigData","Hadoop" , "Spark")
    for (str <- myStrArr) {
      println(str);
    }

    // 实际上，update方法也是类似的，比如:
    val myStrArr = new Array[String](3) //声明一个长度为3的字符串数组，每个数组元素初始化为null
    myStrArr(0) = "BigData" //实际上，调用了伴生类Array中的update方法，执行myStrArr . update(0, "BigData")
    myStrArr(1) = "Hadoop" //实际上，调用了伴生类Array中的update方法，执行myStrArr . update(1, "Hadoop")
    myStrArr(2) = "Spark" //实际上，调用了伴生类Array中的update方法，执行myStrArr . update(2,"Spark")

    myStrArr.foreach(println)
  }
}
/*  从上面可以看出，在进行元组赋值的时候，之所以没有采用Java中的方括号myStrArr[0]，而是采用圆括号的形式，myStrArr(O)，
    是因为存在上述的update方法的机制。
    ●用括号传递给变量(对象)一个或多个参数时，Scala 会把它转换成对apply方法的调用当对带有括号并包括一到若干参数的对象进行赋值时，
    编译器将调用对象的update方法，在调用时，是把括号里的参数和等号右边的对象一起作为update方法的输入参数来执行调用*/

