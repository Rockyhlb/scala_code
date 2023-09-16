package com.hlb

/**
 * 3.3 主要介绍函数式编程基础中的 WordCount 实例
 * Created by IntelliJ IDEA.
 * User: 麦田里的乌鸦
 * Date: 2023 / 9 / 16
 * Time: 15:20
 **/

import java.io.File
import scala.io.Source

object test {
  def main(args: Array[String]): Unit = {
    try {
      // 创建一个File对象dirfile指向需要统计字数的文件夹路径
      val dirfile = new File("D:\\scala_code\\scala_code\\MavenScalaTestWord-3.3\\src\\main\\resources" )
      // 使用dirfile.listFiles方法获取文件夹中的所有文件，并将其存储在files数组中
      val files=dirfile.listFiles
      // 打印获取到的文件路径
      for(file <- files) println(file)
      // 将files数组转换为列表listFiles，这样就使用列表的一些方便的函数来操作它(foreach)
      val listFiles=files.toList
      // 创建一个可变的Map对象wordsMap,用于存储单词和其出现次数的映射关系
      val wordsMap = scala.collection.mutable.Map[String, Int]()

      // 遍历listFiles列表中的每个文件。在遍历过程中，我们使用lambda表达式来打开每个文件，并逐行读取其中的内容。
      // 在每行中，我们使用line.split(" ")将行拆分成单词的数组。然后，我们使用foreach来遍历每个单词，并在wordsMap中更新它们的频率。
      // 如果wordsMap中已经包含该单词，我们就将其频率加1；否则，我们就将该单词添加到wordsMap中，并将其频率初始化为1
      listFiles.foreach(file => Source.fromFile(file).getLines().foreach(line=>line.split(" ").foreach(word => {
        if (wordsMap.contains(word)) {
          wordsMap(word) += 1
        } else {
          wordsMap += (word -> 1)
        }
      }
      )
      )
      )
      // 最后，打印出wordsMap中的所有键值对，即每个单词和它们的频率
      println( wordsMap )
      for((key,value)<-wordsMap) println(key+": "+value)
    }
    catch {
      case ex:Exception => (println(ex))
    }
    finally {
    }
  }
}
