package com.hlb

object test {
//  def main(args: Array[String]): Unit = {
//    // 2.3.1 --> Scala 和 Java在继承方面的区别
///*
//    Scala中的继承与Java有着显著的不同:
//    (1)重写一个非抽象方法必须使用override修饰符。
//    (2)只有主构造器可以调用超类的主构造器。
//    (3)在子类中重写超类的抽象方法时，需要使用override关键字。
//    (4)可以重写超类中的字段。
//    Scala和Java一样不允许类从多个超类继承
//*/
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
//    // 2.3.2 --> 抽象类
//  }
//  abstract class People {
//    val age:Int // 抽象字段必须声明类型
//    def info()  // 抽象方法，不需要使用abstract关键字,无方法体
//    def greeting() {println("hello everyone")}
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
    // 2.3.3 --> 扩展类
    val people1 = new firstPeople()
    people1.info()
    people1.greeting()
    val people2 = new secondPeople()
    people2.info()
    people2.greeting()
  }
}

// 抽象类不能被实例化,因此需要定义扩展类继承抽象类
abstract class People {
  val age:Int // 抽象字段必须声明类型
  def info()  // 抽象方法，不需要使用abstract关键字
  def greeting() {println("hello everyone")}
}

class firstPeople extends People {
  // 重写超类字段，需要使用override关键字
  override val age: Int = 23

  // 重写超类的抽象方法，可加可不加override关键字
  override def info(): Unit = {
    println("First people's age is: " + age)
  }

  // 重写超类的非抽象方法，必须加override关键字
  override def greeting(): Unit = {
    println("The first greeting method is override:")
  }
}

class secondPeople extends People {
  override val age: Int = 18

  override def info(): Unit = {
    println("Second people's age is: " + age)
  }

  override def greeting(): Unit = {
    println("The second greeting method is override:")
  }
}





