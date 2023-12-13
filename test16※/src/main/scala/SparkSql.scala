
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @BelongsProject: test16※
 * @BelongsPackage:
 * @CreateTime : 2023/12/13 20:23
 * @Description: SparkSql: 创建Dataframe及使用SQL来操作数据
 * @Author: code_hlb
 */

// 0、数据分析
// 7499,ALLEN,SALESMAN,7698,1981/2/20,1600,300,30
// 1、定义Emp样例类
case class Emp(empNo:Int,empName:String,job:String,mgr:String,hiredate:String,sal:Int,comm:String,deptNo:Int)

object SparkSql {
  def main(args: Array[String]): Unit = {
    /**
     * 1.实例化SparkContext和SparkSession对象
     * 2.创建case class Emp样例类，用于定义数据的结构信息
     * 3.通过SparkContext对象读取文件，生成RDD[String]
     * 4.将RDD[String]转换成RDD[Emp]
     * 5.引入spark隐式转换函数（必须引入）
     * 6.将RDD[Emp]转换成DataFrame
     * 7.将DataFrame注册成一张视图或者临时表
     * 8.通过调用SparkSession对象的sql函数，编写sql语句
     * 9.停止资源
     */
    // 2、读取数据将其映射成Row对象
    val sc = new SparkContext(new SparkConf().setMaster("local[2]").setAppName("Demo02"))
    // 设置Spark的日志级别,日志级别被设置为 "WARN"，这意味着只会输出警告级别及更高级别的日志信息。
    // 较低级别的日志信息，如信息和调试信息，将不会被输出。这样做可以减少日志输出的数量，使日志更加简洁和易读。
    sc.setLogLevel("WARN")
    val mapRdd = sc.textFile("file:///D:\\Code\\scala_code\\test16※\\src\\main\\resources\\emp.csv")
                 .map(_.split(","))

    val rowRDD: RDD[Emp] = mapRdd.map(line => Emp(line(0).toInt, line(1), line(2), line(3),
                                      line(4), line(5).toInt, line(6), line(7).toInt))

    // 3、创建dataframe
    val spark = SparkSession.builder().getOrCreate()
    // 引入spark隐式转换函数
    import spark.implicits._
    // 将RDD转成Dataframe
    val dataFrame = rowRDD.toDF

    // 4、sql语句操作
    // 4.1 将dataframe注册成一张临时表
    dataFrame.createOrReplaceTempView("emp")
    // 4.2 编写sql语句进行操作
    spark.sql("select deptNo,sum(sal) as total from emp group by deptNo order by total desc").show()

    // 关闭资源
    spark.stop()
    sc.stop()
  }
}
