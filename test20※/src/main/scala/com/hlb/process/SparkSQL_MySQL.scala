package com.hlb.process

import com.hlb.util.{LoggerLevel, SparkUnit}
import java.util.Properties

/**
 * @BelongsProject: test20※
 * @BelongsPackage: com.hlb
 * @CreateTime : 2023/12/15 16:06
 * @Description: SparkSql导入招聘大数据（项目）
 * @Author: code_hlb
 */
object SparkSQL_MySQL extends LoggerLevel{
  def main(args: Array[String]): Unit = {
    /**
     * 读取数据，写入MySQL
     * 实现：
     * 1. 读取文件
     * 2. 转为table操作对象
     * 3. 写SQL查询
     * 4. 保存查询SQL的对象
     * */
    val ss = SparkUnit.getLocalSparkSession("OffLine")

    // 读取csv文件
    val path = "D:\\Code\\scala_code\\test20※\\src\\main\\resources\\"
    val jd_jobsDF = ss.read
      .option("header", false)
      .option("encoding", "gbk")
      .option("delimiter", ",")
      .csv(path + "51_jobs_data.csv").toDF("job_name", "job_date", "minSale", "maxSale", "job_city", "job_area", "company_nature", "company_size_min", "company_size_max", "company_Industry")
    jd_jobsDF.show()
    // SQL式编程需要转为table结构
    jd_jobsDF.createTempView("jd_jobs")

    // sql 查询
    val jobs = ss.sql(
      """
        | SELECT job_name, job_date, minSale, maxSale,job_city,job_area,
        | company_nature,company_size_min,company_size_max,company_Industry
        | from jd_jobs
        |""".stripMargin)

    // JDBC
    val url = "jdbc:mysql://localhost:3306/bigdata?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&serverTimezone=UTC"
    val tb = "jd_jobs"
    val properties = new Properties()
    properties.setProperty("user","root")
    properties.setProperty("password","000000")
    properties.setProperty("driverClass","com.mysql.cj.jdbc.Driver")
    // 将查询对象保存到本地数据库中
    jobs.write.mode("overwrite").jdbc(url, tb, properties)
    // tips ：除了查询SQL返回对象，然后保存对象
    // 还可以直接create table as select ... 一步到位
  }
}
