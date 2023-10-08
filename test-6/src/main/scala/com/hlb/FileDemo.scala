package com.hlb

import scala.collection.mutable

// Scala当中的文件访问
object FileDemo {
  def main(args: Array[String]): Unit = {
    // 读取行
    var source = scala.io.Source.fromFile("D:\\scala_code\\scala_code\\test-6\\src\\main\\resources\\test")
    println(source.mkString)
    println("-------------------")
    source = scala.io.Source.fromFile("D:\\scala_code\\scala_code\\test-6\\src\\main\\resources\\test")
    val lines = source.getLines()
    for(line<-lines) println(line.toString)
    source.close()

    // 读取字符
    source = scala.io.Source.fromFile("D:\\scala_code\\scala_code\\test-6\\src\\main\\resources\\test")
    // 统计字符数
    val map = scala.collection.mutable.Map[Char,Int]()
    for(c<-source){
      if(map.contains(c)){
        map(c) += 1
      }
      else {
        map += (c -> 1)
      }
    }
    println("-------------------")
    for ((k,v)<-map) println(k + ": " + v)
  }
}
