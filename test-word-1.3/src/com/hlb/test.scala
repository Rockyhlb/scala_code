package com.hlb

import scala.collection._
import scala.io.StdIn._

/**
 * 第1小节主要介绍Scala基础
 * 具体包含1.1 --> 基本语法
 *          1.1.1 --> 声明值和属性
 *          1.1.2 --> 基本数据类型和操作
 *          1.1.3 --> Range
 *          1.1.4 --> 控制台输入输出语句
 *          1.1.5 --> 读写文件
 *          1.1.6 --> 异常处理
 *        1.2 --> 控制结构
 *          1.2.1 --> if条件表达式
 *          1.2.2 --> while循环
 *          1.2.3 --> for循环
 *        1.3 --> 数据结构
 *          1.3.1 --> 容器（collection）
 *          1.3.2 --> 列表（List）
 *          1.3.3 --> 集合（Set）
 *          1.3.4 --> 映射（Map）
 *          1.3.5 --> 迭代器（Iterator）
 *          1.3.6 --> 数组（Array）
 *          1.3.7 --> 元组（Tuple）
 * */

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
//    // 1.3.1 --> 容器(collection)
/*
      ●Scala提供了一套丰富的容器( collection)库，包括列表
    (List) 、数组(Array) 、集合(Set)、映射(Map)等
      ●根据容器中元素的组织方式和操作方式，可以区分为有序
    和无序、可变和不可变等不同的容器类别
      ●Scala用了三个包来组织容器类，分别是scala.collection、
    scala.collection.mutable和scala.collection.immutable
*/

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

//    // 1.3.2 --> 列表（List）  &nbsp&nbsp
///*
//      ●列表是一种共享相同类型的不可变的对象序列。既然是一个
//      不可变的集合，Scala的List定义在scala.collection.immutable包中
//      ●不同于Java的java.util.List，scala的List一旦被定义,其值就不能改变，
//      因此声明List时必须初始化
//*/
//    val strList = List("java","C","Python")
///*
//      列表有头部和尾部的概念，可以分别使用head和tail方法来获取
//      head返回的是列表第一个元素的值,tail返回的是除第一个元素外的其它值构成的新列表，
//      这体现出列表具有递归的链表结构
//*/
//    // 两种遍历方法  --> for 循环和 foreach 方法
//    for (str <- strList) println(str);
//    // 使用foreach函数之前要先导入包
//    println("---------------------------------")
//    strList.foreach(str => println(str))
//    println("---------------------------------")
//    println("strList的头部元素是：" + strList.head)
//    println("strList除开头部元素后的新列表是：" + strList.tail)
//
//    // 构造列表常用的方法是通过在已有列表前端增加元素，操作符为 ‘::’
//    val secondList = "PHP"::strList
//    // strList保持不变，secondList成为新列表
//    println("---------------------------------")
//    secondList.foreach(str => println(str))
//
//    // Scala还提供了空列表对象Nil,借助Nil,可以将多个元素用操作符::串起来
//    val thirdList = 1::2::2::Nil  // 与 val list = List(1,2,2)等效
//    println("---------------------------------")
//    thirdList.foreach(int => println(int))
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
//    // 1.3.3 --> 集合（Set）  &nbsp&nbsp
///*
//      ●集合(set)是不重复元素的容器( collection)。
//    列表中的元素是按照插入的先后顺序来组织的，但是，“集合”中的元素并
//    不会记录元素的插入顺序，而是以“哈希”方法对元素的值进行组织
//    所以，它允许你快速地找到某个元素
//      ●集合包括可变集和不可变集，分别位于
//    scala.collection.mutable包和scala.collection.immutable包
//    缺省情况下创建的是不可变集
//*/
//    var firstSet = Set("Java","Scala","C++")
//    println("before add:")
//    firstSet.foreach(str => println(str))
//    firstSet += "JS"
//    println("after add:")
//    firstSet.foreach(str => println(str))
//
//    // 思考以下为什么以下两个set都能插入数据??
//    val secondSet = scala.collection.mutable.Set("hadoop","Spark")
//    secondSet += "Reduce"
//    var thirdSet = scala.collection.immutable.Set("Java","Scala","C++")
//    thirdSet += "bigData"
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
//    // 1.3.4 --> 映射（Map） &nbsp&nbsp
///*
//      ● 映射(Map)是一系列键值对的容器。在一个映射中，键(key)是唯一的，
//    但值(value)不一定是唯一的。可以根据键来对值进行快速的检索
//      ● 和集合一样，Scala采用了类继承机制提供了可变的和不可变的两种映射
//    分别定义在包scala.collection.mutable和scala.collection.immutable里。
//      ● 默认情况下，Scala中使用不可变的映射
//    如果想使用可变映射，必须明确地导入scala.collection.mutable.Map
//*/
//    val firstMap = Map( 1 -> "a",2 -> "b",3 -> "c")
//    // 如果要获取映射的值,可以通过键来获取
//    println(firstMap(1))
//    println(firstMap(2))
//    // 以上方式如何给定的key不存在聚会抛出异常，因此可以先调用contains方法确定key是否存在
//    val res = if (firstMap.contains(4)) firstMap(4) else 0
//    println(res)
//
//    // 不可变映射是无法更新映射中的元素的，如果要更新就应该定义一个可变的映射
//    val secondMap = scala.collection.mutable.Map(1 -> "a",2 -> "b")
//    println("before update:")
//    secondMap.foreach {case (key,value) => printf("(%d,%s)  ",key,value)}
//    println
//    secondMap += (3 -> "c")
//    secondMap(1) = ("aa")
//    println("after update:")
//    secondMap.foreach {case (key,value) => printf("(%d,%s)  ",key,value)}
//
//    // 循环遍历映射
//    println("\n---------------------------------")
//    for((k,v) <- secondMap) println("key: " + k,",value: " + v)
//    // 也可以只遍历key或者values
//    for(k <- secondMap.keys) println("key: " + k)
//    for(v <- secondMap.values) println("value: " + v)
//  }

  // 练习：需求：实现从键盘输入任意0~100的数字,给出优良中，及格不及格的提示
  // 要结合前面所学的变量声明，输入输出，循环语句，判断语句，异常处理，映射的知识点
//  def main(args: Array[String]): Unit = {
//    var continue = true;
//    var score:Float = -1;
//    val scoreMap = Map(1 -> "骚年要努力了呀", 2 -> "有点梦想，但不多~",
//                       3->"勤以修身",4->"勇攀高峰",5->"必拿下！")
//    var grade:Int = -1;
//
//    while (continue) {
//      try {
//        printf("请大声说出你理想的Spark课程成绩(0~100)-->")
//        score=readFloat();
//        if (score > 0 && score < 50) {
//          grade = 1;
//          println(scoreMap(grade))
//          printf("是否继续？(Y or N):")
//          continue = readBoolean()
//        }
//        else if (score >= 60 && score < 69 ){
//          grade = 2;
//          println(scoreMap(grade))
//          printf("是否继续？(Y or N):")
//          continue = readBoolean()
//        }
//        else if (score >= 69 && score < 79 ){
//          grade = 3;
//          println(scoreMap(grade))
//          printf("是否继续？(Y or N):")
//          continue = readBoolean()
//        }
//        else if (score >= 79 && score < 89 ){
//          grade = 4;
//          println(scoreMap(grade))
//          printf("是否继续？(Y or N):")
//          continue = readBoolean()
//        }
//        else if (score >= 89 && score <= 100 ){
//          grade = 5;
//          println(scoreMap(grade))
//          printf("是否继续？(Y or N):")
//          continue = readBoolean()
//        }
//        else {
//          println("输入有误噢~~请重新输入：")
//        }
//      }
//      catch {
//        case ex: NumberFormatException => println("输入有误，请重新输入：")
//      }
//    }
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
//    // 1.3.5 --> 迭代器（Iterator）  &nbsp&nbsp
///*
//    在Scala中，迭代器(Iterator)不是一个集合，但是，提供了访问集合的一种方法
//    迭代器包含两个基本操作:next和hasNext。
//    next可以返回迭代器的下一个元素，hasNext用于检测是否还有下一个元素
//*/
//    val iter = Iterator("a", "b", "c")
//    while (iter.hasNext) {
//      println(iter.next())
//    }
//    for (elem <- iter) {
//      println(elem)
//    }
//
//    //  Iterable有两个方法返回迭代器: grouped和sliding。然而，这些迭代器返回的不是单
//    //  个元素，而是原容器(collection)元素的全部子序列。这些最大的子序列作为参数传
//    //  给这些方法。grouped方法返回元素的增量分块，sliding方法生成一个滑动元素的窗口。
//    //  两者之间的差异通过REPL的作用能够清楚看出。
//    val myList = List(1,2,3,4,5)
//
//    println("\nIterator data:")
//    val itt = myList.iterator
//    while (itt.hasNext) {
//      print(itt.next() +  " ")
//    }
//    //分成长度为3的子列表
//    println("\n\ngroupIterator data:")
//    val groupedIterator = myList grouped 3
//    while (groupedIterator.hasNext) {
//      println(groupedIterator.next())
//    }
//    //滑动窗口
//    println("\nslidingIterator data:")
//    val slidingIterator = myList sliding 3
//    while (slidingIterator.hasNext) {
//      println(slidingIterator.next())
//    }
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

//    def main(args: Array[String]): Unit = {
//    // 1.3.6 --> 数组（Array）
///*
//      数组是一种可变的、可索引的、元素具有相同类型的数据集合，它是各种高
//      级语言中最常用的数据结构。Scala提供了参数化类型的通用数组类Array[T],
//      其中T可以是任意的Scala类型，可以通过显式指定类型或者通过隐式推断来
//      实例化一一个数组。
//*/
//      // val firstArray = new Array(3)
//      val firstArray = new Array[Int](3)
//      firstArray(0) = 12
//      firstArray(1) = 34
//      firstArray(2) = 54
//      firstArray.foreach(int => println(int))
//      // Array提供了函数ofDim来定义二维和三维数组，用法如下:
//      // 可靠secondArray(0)(1)取出元素
////      val secondArray = Array.ofDim(3,4)  // 等价于Array[Array[Int]]
////      val thirdArray = Array.ofDim(3,2,4)// 等价于Array[[Array[Array[Int]]]
//
///*      采用Array类型定义的数组属于定长数组，其数组长度
//      在初始化后就不能改变。如果要定义变长数组，需要
//      使用ArrayBuffer参数类型，其位于包scala.collection.mutable中。*/
//      val aMutableArr = scala.collection.mutable.ArrayBuffer(10,20,30)
//      aMutableArr += 40
//      aMutableArr.insert(2,60,40)
//      aMutableArr -= 40
//      var temo=aMutableArr.remove(2)
//
//      println("-----------------------------")
//      val it = aMutableArr.iterator
//      while (it.hasNext) {
//        println(it.next())
//      }
////      aMutableArr.foreach(int => println(int))
//    }

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
    // 1.3.7 --> 元组（Tuple）
    // 元组是不同类型的值的聚集。元组和列表不同，列表中各个元素必须是相同类型，
    // 而元组可以包含不同类型的元素

    val tuple = ("a",2023,23.7)
    println(tuple._1)
    println(tuple._2)
    println(tuple._3)
  }
}
