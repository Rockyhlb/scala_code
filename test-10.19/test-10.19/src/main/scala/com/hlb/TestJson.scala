package com.hlb

import org.apache.spark.{SparkConf, SparkContext}

import scala.util.parsing.json.JSON

/**
 * @author: code_hlb
 * @date : 2023/10/20 17:59
 */
object TestJson {
  def main(args: Array[String]) {

    val conf = new SparkConf().setAppName("JsonAPP").setMaster("local")
    val sc = new SparkContext(conf)

    val inputFile = "file://D:/scala_code/scala_code/test-10.19/test-10.19/src/main/resources/people.json"
    val jsonStrs = sc.textFile(inputFile)
    val result = jsonStrs.map (s => JSON.parseFull(s))

    result.foreach ({r => r match {
        case Some(map: Map[String, Any]) => println(map)
        case None => println("Parsing failed")
        case other => println("Unknown data structure: " + other)
      }
    }
    )
  }
}