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
 * */

object test {
//  def main(args: Array[String]): Unit = {
//    // 1.2.1 --> if条件循环
//    val y: Int = 3;
//    if (y > 0) {
//      println(y + " > 0 ~")
//    }
//    else if (y == 3) {
//      println(y + " = 3~")
//    }
//    else {
//      println(y + " < 3~")
//    }
//    // 有一点与java不同的是，Scala中的if表达式的值可以赋给变量
//    val x = 2
//    val a = if (x > 1) 1 else -1
//    println("a = " + a);
//  }

//  def main(args: Array[String]): Unit = {
//    // 1.2.2 --> while循环
//    var i = 9
//    while (i > 0){
//      i /= 2
//      println("i is: " + i )
//    }
//
//    var j: Int = 0;
//    do {
//      j += 1
//      printf("j = %d  ",j)
//    }while (j < 5)
//  }

//  def main(args: Array[String]): Unit = {
//    // 1.2.3 --> for循环  $nbsp --> 重点
//    // for (变量 <- 表达式) 语句块  "变量 <- 表达式"被称为生成器(generator)
//    for (i <- 1 to 5) {
//      println(i)
//    }
//    println("nbspnbspnbspnbsp")
//    for (i <- 1 to 5 by 2) {
//      println(i)
//    }
//
///*    不希望打印出所有的结果，过滤出一些满足制定条件的结
//    果，需要使用到称为“守卫(guard)”的表达式
//    ●比如，只输出1到5之中的所有偶数，可以采用以下语句:*/
//    println("nbspnbspnbspnbsp")
//    for (i <- 1 to 5 if i%2 == 0) {
//      println(i)
//    }
//
//    // Scla 也支持"多个生成器的情形"，用分号把它们分开
//    println("nbspnbspnbspnbsp")
//    for (i <- 1 to 5 if i%2 == 0;j <- 1 to 2 if j != i) {
//      println(i*j)
//    }
//
///*  ●Scala的for结构可以在每次执行的时候创造一个值，然后
//    将包含了所有产生值的集合作为for循环表达式的结果返回，
//    集合的类型由生成器中的集合类型确定
//    ●通过for循环遍历一个或多个集合，对集合中的元素进行“推
//    导”，从而计算得到新的集合，用于后续的其他处理
//    for (变量 <-表达式)  yield { 语句块}  */
//    println("nbspnbspnbspnbsp")
//    val res = for (i <- 1 to 5 if i%2 == 0) yield {println(i);i}
//    println(res.length)
//    println(res.head)
//    // 返回除头元素后构成的新列表
//    println(res.tail)
//    // apply(index)根据下标取集合或数组中的第index个元素
//    println(res.apply(0))
//  }
}
