package com.hlb.util

import org.apache.spark.sql.SparkSession

/**
 * @BelongsProject: test19-20
 * @BelongsPackage: com.hlb.util
 * @CreateTime : 2023/12/8 20:12
 * @Description: TODO
 * @Author: code_hlb
 */
object SparkUnit {
  /**
   * 一个class参数
   * */
  def getLocalSparkSession(appName: String): SparkSession = {
    SparkSession.builder().appName(appName).master("local[2]").getOrCreate()
  }

  def getLocalSparkSession(appName: String, support: Boolean): SparkSession = {
    if (support) SparkSession.builder().master("local[2]").appName(appName).enableHiveSupport().getOrCreate()
    else getLocalSparkSession(appName)
  }

  def getLocalSparkSession(appName: String, master: String): SparkSession = {
    SparkSession.builder().appName(appName).master(master).getOrCreate()
  }

  def getLocalSparkSession(appName: String, master: String, support: Boolean): SparkSession = {
    if (support) SparkSession.builder().appName(appName).master(master).enableHiveSupport().getOrCreate()
    else getLocalSparkSession(appName, master)
  }

  def stopSpark(ss: SparkSession) = {
    if (ss != null) {
      ss.stop()
    }
  }
}
