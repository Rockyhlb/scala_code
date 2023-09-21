package com.hlb

/**
 * 2.1 主要介绍面向对象编程基础中的 类
 * Created by IntelliJ IDEA.
 * User: 麦田里的乌鸦
 * Date: 2023 / 09 / 13
 * Time: 15:20
 **/

object test {

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

  //  def main(args: Array[String]): Unit = {
  //    // 2.1.1 --> 简单的类
  //    // 简单的类的定义：
  //    class Counter {
  //      // 定义的字段和方法
  //    }
  //    // 可以使用 new 关键字来生成对象
  //    new Counter()
  //    new Counter
  //  }
  //}

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

  //  def main(args: Array[String]): Unit = {
  //    // 2.1.2 --> 给类增加字段和方法
  //    class Print1 {
  //      // 定义的字段和方法
  //      private var value = 0;
  //      def write(): Unit ={
  //        println("value = " + value)
  //      }
  //      def current(int: Int) {value = int}
  //    }
  //
  //    // Unit后面的等号和大括号后面，包含了该方法要执行的具体操作语句
  //    //如果大括号里面只有一行语句，那么也可以直接去掉大括号，写成下面的形式:
  //    class Print2 {
  //      // 定义的字段和方法
  //      private var value = 0;
  //      def write(): Unit = println("value = " + value)
  //      def current(int: Int) {value = int}
  //    }
  //
  //    // 或者，还可以去掉返回值类型和等号，只保留大括号，如下:
  //    class Print3 {
  //      // 定义的字段和方法
  //      private var value = 0;
  //      def write() {
  //        println("value = " + value)
  //      }
  //      def current(int: Int) {
  //        value = int
  //      }
  //    }
  //  }
  //}

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

  //  def main(args: Array[String]): Unit = {
  //    // 2.1.3 --> 创建对象
  //    class Print {
  //      // 定义的字段和方法
  //      private var value = 0;
  //
  //      def write() {
  //        println("value = " + value)
  //      }
  //
  //      def current(int: Int) {
  //        value = int
  //        println("current value = " + value)
  //      }
  //    }
  //    // 新建对象，并调用其中方法，
  //    val pt = new Print()
  //    pt.write()
  //    pt.current(23)
  //    pt.write()
  //    // Scala在调用无参方法时，可以省略圆括号
  //    pt.write
  //  }
  //}

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

  //  def main(args: Array[String]): Unit = {
  //    // 2.1.4 --> 在linux环境下应该如何编译和执行？
  //    // 一、新建一个test.scala代码文件
  //    // 二、在Linux Shell命令提示符下，使用scala命令执行代码文件,scala test.scala
  //    // 三、也可以进入Scala解释器下执行test.scala
  //    //  (1)、输入“scala”，进入解释器
  //    //  (2)、load /完整项目路径/test.scala
  //    // 四、也可以通过scalac对test.scala进行编译
  //    //  (1)、scalac test.scala
  //    //  (2)、若编译失败，检查一下源代码中的声明是否被封装在对象中，要含有main入口
  //    //  (3)、编译完成以后，scala命令执行，scala -classpath . mainName
  //    //  (4)、上面‘.’表示当前目录，‘mainName’指包含main方法的对象名称
  //  }

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

  //  def main(args: Array[String]): Unit = {
  //    // 2.1.5 --> getter和setter方法
  //    /*
  //        ● 给类中的字段设置值以及读取值,在Java中是通过getter和setter方法实现的
  //        ● 在Scala中，也提供了getter和setter方法的实现，但是并没有定义成getXxx和tXxx
  //  */
  //    val read = new Read()
  //    // 原始值
  //    println("Original value=" + read.value)
  //    // 设置新值
  //    read.value = 23
  //    println("new value=" + read.value)
  //    // 设置自增3
  //    read.increment(3)
  //    println("after increment:" + read.value)
  //  }
  //
  //  class Read {
  //    // value变成私有字段以后,Scala又没有提供getter和setter方法
  //    // 解决方案是，在Scala中,可以通过定义类似getter和setter的方法
  //    // 分别叫做value和value_=，具体如下:
  //    private var privateValue = 0
  //
  //    // getter 方法
  //    def value: Int = privateValue
  //
  //    // setter 方法
  //    def value_=(newNum: Int) {
  //      if (newNum > 0) {
  //        privateValue = newNum
  //      }
  //    }
  //
  //    def increment(int: Int): Unit = {
  //      value += int
  //    }
  //
  //    def current(): Int = {
  //      value
  //    }
  //  }
  //}

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

//  def main(args: Array[String]): Unit = {
//    // 2.1.6 --> 辅助构造器
//    /*
//      ● Scala构造器包含1个主构造器和若干个(0个或多个)辅助构造器
//      ● 辅助构造器的名称为this，每个辅助构造器都必须调用一个此前已经定义的
//        辅助构造器或主构造器
//      ●下面定义一个带有辅助构造器的类，我们对上面的Read类定义进行修改:
//  */
//
//    val mainRead = new Read(); // 主构造器
//    // 第一个辅助构造器，只录入姓名
//    val firstRead = new Read("hlb")
//    // 第二个辅助构造器，录入姓名和年纪
//    val secondRead = new Read("hlb", 18)
//
//    println("This is mainRead:")
//    mainRead.increment(18)
//    mainRead.info()
//    println("主构造器信息完整度：" + mainRead.current())
//
//    println("This is firstRead:")
//    firstRead.increment(18)
//    firstRead.info()
//    println("辅助构造器信息录入完整度：" + firstRead.current())
//
//    println("This is secondRead:")
//    secondRead.increment(20)
//    secondRead.info()
//    println("辅助构造器录入信息完整度：" + secondRead.current())
//  }
//
//  class Read {
//    private var name = ""
//    private var age = 0
//    private var mode = false // 标记是否成功录全信息
//
//    // 第一个辅助构造器
//    def this(name: String) {
//      this() //调用主构造器
//      this.name = name
//    }
//
//    // 第二个辅助构造器
//    def this(name: String, age: Int) {
//      this(name) //调用前一个辅助构造器
//      this.age = age
//      this.mode = true
//    }
//
//    def increment(int: Int): Unit = {
//      age += int
//    }
//
//    def current(): Boolean = {
//      mode
//    }
//
//    def info(): Unit = {
//      printf("Name is: %s, Age is: %d ", name, age)
//    }
//  }
//}

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
    // 2.1.7 --> 主构造器
    /*
        ● Scala的每个类都有主构造器。但是，Scala的主构造器和Java有着明显的不同，
        Scala的主构造器是整个类体，需要在类名称后面罗列出构造器所需的所
        有参数，这些参数被编译成字段，字段的值就是创建对象时传入的参数的值。
        ● 对于上面设置name和age的例子，刚才我们是使用辅助构造器来
        对name和age的值进行设值，现在我们重新来一次，这次我们转而采用主构
        造器来设置name和age的值。
    */
    val read = new Read("hlb",18)
    read.info()
    read.increment(2)
    println("\nAge is changed? --> " + read.current())
    println("Current age is: " + read.age)
  }

  class Read(val name: String,var age:Int) {
    private var mode = false
    def increment(int: Int) {age += int;mode = true}
    def current():Boolean = { mode }
    def info(): Unit = {
      printf("Name is: %s, Age is: %d ",name,age)
    }
  }
}
