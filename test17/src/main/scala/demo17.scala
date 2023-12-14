import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types.{DataTypes, StructField, StructType}

/**
 * @BelongsProject: test17
 * @BelongsPackage:
 * @CreateTime : 2023/12/14 20:55
 * @Description: 根据Dataframe的定义来创建DataFrame并且使用DSL语言来分析数据
 * @Author: code_hlb
 */
object demo17 {
  def main(args: Array[String]): Unit = {
    // 1.创建SparkContext和SparkSession对象
    val sc = new SparkContext(new SparkConf().setAppName("Demo17").setMaster("local[2]"))
    val sparkSession = SparkSession.builder().getOrCreate()
    //val spark = SparkSession.builder().appName("SparkSessionAPP").master("local[2]").getOrCreate()
    // 2. 利用StructType类型构建schema，用于定义数据的结构信息
    val mySchema = StructType(List(
      StructField("empno", DataTypes.IntegerType, false),
      StructField("ename", DataTypes.StringType, false),
      StructField("job", DataTypes.StringType, false),
      StructField("mgr", DataTypes.StringType, false),
      StructField("hiredate", DataTypes.StringType, false),
      StructField("sal", DataTypes.IntegerType, false),
      StructField("comm", DataTypes.StringType, false),
      StructField("deptno", DataTypes.IntegerType, false)
    ))
    // 3. 通过SparkContext对象读取文件，生成RDD
    val empRDD = sc.textFile("file:///D:\\Code\\scala_code\\test17\\src\\main\\resources\\emp.csv")
    // 4. 将RDD[String]映射成RDD[Row]
    val rowRDD = empRDD.map(line => {
      val strings = line.split(",")
      Row(strings(0).toInt, strings(1), strings(2), strings(3), strings(4), strings(5).toInt,
          strings(6), strings(7).toInt)
    })
    // 5. 通过SparkSession对象创建dataframe
    val dataFrame = sparkSession.createDataFrame(rowRDD, mySchema)
    // 6. 展示内容 DSL， DSL 是一种用于简化特定领域任务的语言
    dataFrame.groupBy("deptno").sum("sal").as("result").sort("sum(sal)").show()
  }
}
