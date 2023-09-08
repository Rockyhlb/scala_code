package com.hlb
import java.lang._

import scala._
import scala.collection.immutable.Range.Int
import scala.io.StdIn._
import scala.io.Source
import java.io.PrintWriter
import java.io.FileNotFoundException
import java.io.IOException

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
 *        1.4 --> 面向对象编程基础
 *        1.5 --> 函数式编程基础
 * */

object test {
//  def main(args: Array[String]): Unit = {
//    // 1.1 基本语法
//    //  1.1.1 声明值和属性
//    // val --> 不可变，声明时就被赋值
//    val mystr = "hello Val";
//    // var --> 可变，声明时需要进行初始化
//    var myStr = "Hello Var~"
//    myStr = "hello var";
//    println(mystr)
//    println(myStr)
//
//    // 两种声明变量的方式
//    var myPrice1 : Double = 9.2
//    var myPrice2 = 9.9
//  }

//  def main(args: Array[String]): Unit = {
//    // 1.1.2 --> 基本数据类型和操作
//    // (Byte Char Short Int Long Float Double Boolean）都是类，
//    // 都是包scala的成员  例如:scala.Int
//    // 但是对于字符串，Scala用java.lang.String类来表示字符串
//    // 字面量（literal）:什么数据类型就是什么字面量，例:整数字面量
//
//    // 操作符：Scala当中的‘+’‘-’‘*’‘/’‘%’这些操作符都是方法，
//    // 例如：5 + 3 和（5）.+（3）就是等价的
//    // 即：a 方法 b <==>  a.方法b
//    val sum1 = 5 + 3; // 实际上调用了（5）.+ (3)
//    // 但是Scala当中并没有 '++' 和 '--' 操作符，可以使用+=，-=代替
//
//    /**
//       富包装类.
//        ●对于基本数据类型，除了以上提到的各种操作符外，Scala
//      还提供了许多常用运算的方法，只是这些方法不是在基本类
//      里面定义，还是被封装到一个对应的富包装类中
//      每个基本类型都有一个对应的富包装类，例如Int有-一个
//      RichInt类、String有 - -个RichString类，这些类位于包
//      scala.runtime中
//      ●当对一个基本数据类型的对象调用其富包装类提供的方法,
//      Scala会自动通过隐式转换将该对象转换为对应的富包装类
//      型，然后再调用相应的方法。例如: 3 max 5
//    */
//    val min = 2 min 4
//    println(min);
//  }

//  def main(args: Array[String]): Unit = {
//    // 1.1.3 --> Range
//    /*
//    *   在执行for循环时，我们经常会用到数值序列，比如，i的值
//    *   从1循环到5，这时就可以采用Range来实现.Range可以支持
//    *   创建不同数据类型的数值序列，包括Int、Long、Float、
//    *   Double、 Char、 BigInt和BigDecimal等
//    */
//    // 1、创建一个从1到5的数值序列，包含区间终点5，步长为1
//    val range1 = 1 to 5;
//    range1.foreach(println)
//    println()
//    // 2、创建一个从1到5的数值序列，不包含区间终点5，步长为1
//    val range2 = 1 until 5;
//    range2.foreach(println)
//    println()
//    // 3、创建一个从1到10的数值序列，包含区间终点10，步长为2
//    val range3 = 1 to 10 by 2
//    range3.foreach(println)
//    println()
//    // 4、创建一个Float类型的数值序列,从0.5到5.9f,步长为0.8f
//    val range4 = 0.5f to 5.9f by 0.8f
//    range4.foreach(println)
//  }

//  def main(args: Array[String]): Unit = {
//    // 1.1.4 --> 控制台输入输出语句  以read为前缀的方法 "readLine"
//    // 所有这些函数都属于scala.io.Stdln的方法，使用前必须导入
//    print("请输入任意整数：")
//    val read1 = readInt()
//    println("您输入的整数为：" + read1)
//
//    print("请输入任意布尔类型：")
//    val read2 = readBoolean()
//    println("您输入的布尔类型为：" + read2)
//    printf("hello,my age is %d, i am a person:%s",read1,read2)
//  }

//  def main(args: Array[String]): Unit = {
//    // 1.1.5 --> 读写文件
//    // 写入文件  -->  Scala需要引入java.io.PrintWriter实现把数据写入到文件
//    val out = new PrintWriter("test.txt")  // 此处可以修改为绝对路径
//    for (i <- 1 to 5) out.println(i)
//    out.close()
//
//    // 读取文件 --> 可以使用Scala.io.Source的getLines方法实现对文件中所有行的读取
//    val inputTestFile = Source.fromFile("test.txt")
//    val lines = inputTestFile.getLines // 返回一个迭代器进行遍历
//    for (line <- lines) println(line)
//  }

//  def main(args: Array[String]): Unit = {
//    // 1.1.6 --> 异常处理
//    // 注:Scala不支持Java中的“受检查异常”,因此把所有异常都当作“运行时异常”
//    // file变量如果是在try块中定义的，在finally块中无法访问它
//    val file: Option[Source] = None
//    try {
//      val file = Source.fromFile("input.txt")
//      for (line <- file.getLines()) {
//        println(line)
//      }
//    }
//    catch {
//      case ex: FileNotFoundException =>
//           println("FileNotFoundException~")
//      case ex:IOException =>
//           println("IOException~~")
//    }
//    finally {
//      file.foreach(_.close())
//    }
//  }
}
