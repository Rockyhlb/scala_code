package com.hlb

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author: code_hlb
 * @date : 2023/10/21 16:00
 */
object testDemo13 {
  // Spark RDD 求员工工资总额
  def main(args: Array[String]): Unit = {
    /*二、需求：
    任务1、根据员工工资表（emp.csv），求出各部门员工总工资
    任务2、对各部门工资开销情况按部门进行排序（升序）
    任务3、对各部门工资开销情况，按照部门员工总工资进行排序（升序）

    三、问题解决：
    1、读取员工工资表（emp.csv）
    2、获取员工工资信息和部门信息
    3、对各部门工资进行汇总
    4、按部门进行排序（升序）*/

    //1、创建conf 和 sc
    val conf = new SparkConf().setAppName("EmpSalByDeptNo").setMaster("local[2]")
    val sc = new SparkContext(conf)
    // 2.业务逻辑（重点）
    sc.textFile("file:\\D:\\scala_code\\scala_code\\test-10.21\\test-10.21\\src\\main\\resources\\emp.csv")
      .map(x => {
        val strings = x.split(",")
        val sal = strings(5).toInt
        val deptNo = strings(7).toInt
        (deptNo,sal)
      }).reduceByKey(_+_).sortByKey(ascending = true).collect().foreach(println)

    //3.关闭资源
    sc.stop()
  }
}
