package com.hlb

import org.apache.spark.{Partitioner, SparkConf, SparkContext}

/**
 * @author: code_hlb
 * @date : 2023/10/19 16:20
 *
 *       实例：根据key值的最后一位数字，写到不同的文件
 *       例如：
 *       10写入到part-00000
 *       11写入到part-00001
 *       .
 *       .
 *       .
 *       19写入到part-00009
 */

//自定义分区类，需继承Partitioner类
class UsridPartitioner(numParts:Int) extends Partitioner{
  //覆盖分区数
  override def numPartitions: Int = numParts
  //覆盖分区号获取函数
  override def getPartition(key: Any): Int = {
    key.toString.toInt % 10
  }
}

object TestDemo {
  def main(args: Array[String]) {
//    val conf = new SparkConf()
    val sparkConf = new SparkConf().setAppName("writeToFile").setMaster("local")

    val sc = new SparkContext(sparkConf)
    //模拟5个分区的数据
    val data = sc.parallelize(1 to 10, 5)
    //根据尾号转变为10个分区，分写到10个文件
    data.map((_, 1)).partitionBy(new UsridPartitioner(10)).saveAsTextFile("D:\\scala_code\\scala_code\\test-10.19\\test-10.19\\src\\main\\resources\\output")
  }
}
