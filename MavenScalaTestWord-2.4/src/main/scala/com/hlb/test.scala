package com.hlb

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
//    // 2.4.1 --> 特质
///*
//    ● Java中提供了接口,允许一个类实现任意数量的接口
//    ● 在Scala中没有接口的概念,而是提供了“特质(trait)",它不仅实
//    现了接口的功能还具备了很多其他的特性
//    ● Scala的特质,是代码重用的基本单元，可以同时拥有抽象方法和
//    具体方法
//    ● Scala中,一个类只能继承自一个超类，却可以实现多个特质，从
//    而重用特质中的方法和字段，实现了多重继承
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
//    // 2.4.2 --> 特质的定义
//  }
//
//  // 特质的定义和类的定义很相似,只是用的是"trait"关键字
//  trait People {
//    // 抽象字段
//    var age = Int
//    // 抽象方法(不需要使用abstract关键字)
//    def currentAge(): Int
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
//    // 2.4.3 --> 把特质混入类中
//    val people1 = new firstPeople
//    println("FirstPeople's age is: " + people1.currentAge())
//    val people2 = new secondPeople
//    println("SecondPeople's age is: " + people2.currentAge())
//  }
//}
//
//trait People {
//  // 抽象字段
//  var age:Int
//  // 抽象方法(不需要使用abstract关键字)
//  def currentAge(): Int
//}
//
//// 使用extends或with关键字把特质混入类中
//class firstPeople extends People {
//  override var age: Int = 18
//
//  override def currentAge(): Int = {
//    age += 5
//    age
//  }
//}
//
//// 使用extends或with关键字把特质混入类中
//class secondPeople extends People {
//  override var age:Int = 20
//  override def currentAge():Int = {
//    age += 5
//    age
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
//    // 2.4.4 --> 特质可以包含具体实现
//    val peo = new firstPeople
//    peo.greeting("hello,Scala~")
//  }
//}
//
//trait People {
//  def greeting(msg:String) {
//    println(msg)
//  }
//}
//
//class firstPeople extends People {
//
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
//    // 2.4.5 --> 把多个特质混入类中  ~~~extends ... with~~~
//    val peo = new firstPeople()
//    peo.age = 20
//    peo.greeting("hello fistPeople: ")
//    println("my age is: " + peo.currentAge())
//  }
//}
//
//trait PeopleGreeting {
//  def greeting(msg:String) {
//    println(msg)
//  }
//}
//
//trait PeopleInfo {
//  // 抽象字段
//  var age:Int
//  // 抽象方法(不需要使用abstract关键字)
//  def currentAge(): Int
//}
//
//class firstPeople extends PeopleGreeting with PeopleInfo {
//  override var age: Int = 13
//
//  override def currentAge(): Int = {
//    age += 5
//    age
//  }
}

