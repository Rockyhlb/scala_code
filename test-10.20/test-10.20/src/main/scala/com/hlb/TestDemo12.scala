package com.hlb

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author: code_hlb
 * @date : 2023/10/20 15:41
 */
object TestDemo12 {

  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setAppName("test12").setMaster("local")
    val sc = new SparkContext(sparkConf)
  }
}
