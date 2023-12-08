import org.apache.spark.{SparkConf, SparkContext}

/**
 * @BelongsProject: test14
 * @BelongsPackage:
 * @CreateTime : 2023/12/8 17:57
 * @Description: Spark RDD 求员工工资总额
 * @Author: code_hlb
 */
object EmpSalByDeptNo {
    def main(args: Array[String]): Unit = {
      /**
       * 需求：
       * 任务1、根据员工工资表（emp.csv），求出各部门员工总工资
       * 任务2、对各部门工资开销情况按部门进行排序（升序）
       * 任务3、对各部门工资开销情况，按照部门员工总工资进行排序（升序）
       *
       * 解决思路：
       * 1、读取员工工资表（emp.csv）
       * 2、获取员工工资信息和部门信息
       * 3、对各部门工资进行汇总
       * 4、按部门进行排序（升序）
       */
      //1、创建conf 和 sc
      val conf = new SparkConf().setAppName("EmpSalByDeptNo").setMaster("local[2]")
      val sc = new SparkContext(conf)
      // 2.业务逻辑（重点）
      sc.textFile("file:\\D:\\Code\\scala_code\\test13\\src\\main\\resources\\emp.csv")
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
