package com.hlb

import org.apache.spark.{SparkConf, SparkContext}

object WordCount {
  def main(args: Array[String]): Unit = {
    // 设置Hadoop的安装目录，这是为了让Spark能够正确找到Hadoop的相关文件。
//    System.setProperty("hadoop.home.dir", "D:\\hadoop\\hadoop-2.8.0")

    // 创建SparkConf对象，设置了应用程序的名称为"WordCount"，并且设置了Spark的运行模式为本地模式。
    val sparkConf = new SparkConf().setAppName("WordCount").setMaster("local")
    // 创建SparkContext对象，SparkContext是Spark的主要入口点，用于与集群进行交互。
    val sc = new SparkContext(sparkConf)
    // 设置日志级别，将Spark的日志级别设置为WARN，以减少日志输出。
    sc.setLogLevel("WARN")
    //  读取文本文件并进行词频统计，这里使用textFile方法读取文本文件，
    //  然后通过flatMap和map方法对每行文本进行分词和映射操作，
    //  之后使用reduceByKey方法进行词频统计。
    val resultArray = sc.textFile(path = "file:///D:\\scala_code\\scala_code\\WordCount\\WordCount\\src\\main\\resources\\text")
      .flatMap(_.split(" "))
      .map((_, 1))
      .reduceByKey(_ + _)
    // 使用collect方法将结果收集到Driver程序中，并通过foreach(println)方法逐行打印结果。
      .collect()
      .foreach(println)
    sc.stop()
  }
}
