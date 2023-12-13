import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @BelongsProject: test15※
 * @BelongsPackage:
 * @CreateTime : 2023/12/12 19:57
 * @Description: SparkSQL入门操作，以下代码均需在 spark-shell 上运行~~  因此idea中报红属正常情况
 *               注：拷贝代码到集群上运行时，一定要确保hdfs上确实有该文件，并且应该灵活更改自己的文件路径
 * @Author: code_hlb
 */
object SparkSql {
  // 对结构化数据的读取
  def main1(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("sql").setMaster("local[2]")
    val sc = new SparkContext();

    // 定义了一个名为Emp的样例类（case class），表示员工的数据结构，可以在后面的代码中使用它来创建DataFrame。
    case class Emp(empno: Int, ename: String, job: String, mgr: String, hiredate: String, sal: Int,
                   comm: String, deptno: Int)
    /**
     * 读取"hdfs://hadoop100:9000/input/sqlTest/emp.csv"文件，并将每一行以逗号为分隔符拆分为数组。
     * sc代表SparkContext对象，用于与Spark集群进行通信。
     * textFile函数将文本文件加载为RDD（弹性分布式数据集），每个元素是文件中的一行文本。
     */
    val lines = sc.textFile("hdfs://hadoop100:9000/input/sqlTest/emp.csv")
      .map(_.split(","))

    // 将拆分的每一行数据映射为Emp对象
    val allEmp = lines.map(x => Emp(x(0).toInt, x(1), x(2), x(3), x(4), x(5).toInt,
      x(6), x(7).toInt))
    // 将RDD转换为DataFrame，使用toDF方法将RDD中的元素转换为DataFrame的行。
    // Spark可以自动推断出DataFrame的模式（即列名和数据类型），并将其应用于转换后的DataFrame。
    val allEmpDF = allEmp.toDF
    // 默认显示DataFrame的前20行，以及它的模式信息
    allEmpDF.show
  }

  def main2(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("sql").setMaster("local[2]")
    val sc = new SparkContext()
    val spark = new SparkSession()
    // StructType 是个case class,一般用于构建schema（模式）, 因为是case class,所以使用的时候可以不用new关键字
    // 通过StructType构造函数自定义schema，我们定义了每个字段的名称和数据类型，并将其作为StructField对象添加到List中。
    import org.apache.spark.sql.types._
    val myschema = StructType(List(
      StructField("empno", DataTypes.IntegerType),
      StructField("ename", DataTypes.StringType),
      StructField("job", DataTypes.StringType),
      StructField("mgr", DataTypes.StringType),
      StructField("hiredate", DataTypes.StringType),
      StructField("sal", DataTypes.IntegerType),
      StructField("comm", DataTypes.StringType),
      StructField("deptno", DataTypes.IntegerType)
    ))
    val empcsvRDD = sc.textFile("hdfs://hadoop100:9000/input/sqlTest/emp.csv").map(_.split(","))
    // 创建一个基于RDD的Row对象数据集，通过对每一行进行处理，将CSV文件中的每个字段按照指定的类型转换为相应的数值，并构建Row对象。
    import org.apache.spark.sql.Row
    val rowRDD = empcsvRDD.map(line => Row(line(0).toInt, line(1), line(2), line(3), line(4),
                               line(5).toInt, line(6), line(7).toInt))
    // spark是一个SparkSession对象，它是Spark 2.0版本引入的新概念。它是使用Spark SQL进行结构化数据处理的入口点。
    // 使用SparkSession的createDataFrame方法将基于Row的RDD和之前定义的自定义Schema传入，从而创建了最终的DataFrame对象。
    // 然后使用show方法来显示DataFrame的内容。
    val df = spark.createDataFrame(rowRDD, myschema)
    df.show
  }

  // 读取json文件
  def main3(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("sql").setMaster("local[2]")
    val sc = new SparkContext(conf)
    val spark = new SparkSession()
    /**
     * spark是一个SparkSession对象，read是SparkSession的方法，用于读取数据。
     * json是读取JSON文件的方法，括号内的字符串则是JSON文件在HDFS(分布式文件系统)的路径。
     * 最后返回的是一个DataFrame对象
     * 表emp.json的列名：【comm | deptno | empno | ename | hiredate | job | mgr | sal】
     */
    val df = spark.read.json("hdfs://hadoop100:9000/input/sqlTest/emp.json")
    df.show

    // 使用DataFrame的select方法选择指定的列名，并使用show方法展示结果
    df.select ("ename").show
    df.select($"ename").show
    df.select($"ename",$"sal",$"sal"+100).show
    // 使用DataFrame的filter方法筛选满足条件的行，并使用show方法展示结果
    df.filter($"sal">2000).show
    // 使用DataFrame的groupBy方法对指定的列进行分组，并使用count方法计算每个分组的行数，最后使用show方法展示结果。
    df.groupBy($"deptno").count.show

    // 使用DataFrame的createOrReplaceTempView方法创建一个临时视图，名称为"emp"
    df.createOrReplaceTempView("emp")
    // 使用SparkSession的sql方法执行SQL查询语句，并使用show方法展示结果
    spark.sql("select * from emp").show
    spark.sql("select * from emp where deptno=10").show
    spark.sql("select deptno,sum(sal) from emp group by deptno").show

    //1 创建一个普通的 view(视图) 和一个全局的 view
    df.createOrReplaceTempView("emp1")
    df.createGlobalTempView("emp2")
    //2 在当前会话中执行查询，均可查询出结果
    spark.sql("select * from emp1").show
    spark.sql("select * from global_temp.emp2").show
    //3 开启一个新的会话，执行同样的查询
    spark.newSession.sql("select * from emp1").show  // 运行出错
    spark.newSession.sql("select * from global_temp.emp2").show // 正常运行
  }

  // 创建 Dataset
  def main4(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("sql").setMaster("local[2]")
    val sc = new SparkContext(conf)
    val spark = new SparkSession()

    // 方式一：使用序列
    // 1、定义 case class
    case class MyData(a: Int, b: String)
    // 2、生成序列，并创建 DataSet
    val ds = Seq(MyData(1, "Tom"), MyData(2, "Mary")).toDS
    // 3、查看结果
    ds.show
    ds.collect

    // 方式二：使用 JSON 数据
    //1、定义 case class
    case class Person(name: String, gender: String)
    //2、通过 JSON 数据生成 DataFrame
    val df = spark.read.json(sc.parallelize("""{"gender": "Male", "name": "Tom"}""" :: Nil))
    //3、将 DataFrame 转成 DataSet
    df.as[Person].show
    df.as[Person].collect

    // 方式三：使用 HDFS 数据
    val linesDS = spark.read.text("hdfs://hadoop100:9000/input/word.txt").as[String]
    val words = linesDS.flatMap(_.split(" ")).filter(_.length > 3)
    words.show
    words.collect
    val result = linesDS.flatMap(_.split(" ")).map((_, 1)).groupByKey(x => x._1).count
    result.show
    result.orderBy($"value").show
  }

  // Dataset 的操作案例
  def main5(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("sql").setMaster("local[2]")
    val sc = new SparkContext(conf)
    val spark = new SparkSession()

    //1.通过read.json方法生成DataFrame对象empDF，表示员工信息
    val empDF = spark.read.json("hdfs://hadoop100:9000/input/sqlTest/emp.json")
    //查询工资大于等于 3000 的员工
    empDF.where($"sal" >= 3000).show
    //创建 case class，定义员工的数据结构
    case class Emp(empno: Long, ename: String, job: String, hiredate: String, mgr: String,
                   sal: Long, comm: String, deptno: Long)

    // 将empDF转换为DataSet类型的empDS
    val empDS = empDF.as[Emp]
    // 对empDS进行过滤，查询工资大于3000的员工，并显示结果。
    empDS.filter(_.sal > 3000).show
    //查看 10 号部门的员工
    empDS.filter(_.deptno == 10).show

    //多表查询
    //1、创建部门表
    val deptRDD = sc.textFile("hdfs://hadoop100:9000/input/sqlTest/dept.csv").map(_.split(","))
    // 创建了一个名为Dept的case class，用于定义部门的数据结构
    case class Dept(deptno: Int, dname: String, loc: String)
    // 使用map方法将deptRDD中的每一行数据转换为Dept对象，并将其转换为DataSet类型的deptDS
    val deptDS = deptRDD.map(x => Dept(x(0).toInt, x(1), x(2))).toDS
    //2、创建员工表
    case class Emp(empno: Int, ename: String, job: String, mgr: String, hiredate: String,
                   sal: Int, comm: String, deptno: Int)
    val empRDD = sc.textFile("hdfs://hadoop100:9000/input/sqlTest/emp.csv").map(_.split(","))
    val empDS = empRDD.map(x => Emp(x(0).toInt, x(1), x(2), x(3), x(4), x(5).toInt, x(6), x(7).toInt)).toDS
    //3、执行多表查询：等值链接
    val result = deptDS.join(empDS, "deptno")
    result.show
    result.explain
    //另一种写法：注意有三个等号
    val result = deptDS.joinWith(empDS, deptDS("deptno") === empDS("deptno"))
    //查看执行计划：
    result.show
    result.explain
  }

  // 把DF保存成文件
  def main6(args: Array[String]): Unit = {
    // 1、write.format()支持输出 json,parquet, jdbc, orc, libsvm, csv, text等格式文件
    val peopleDF = spark.read.format("json").load("hdfs://hadoop100:9000/input/sqlTest/people.json")
    peopleDF.show
    // 选择name和age这两列，并使用write.format("csv").save()方法将结果保存为CSV文件
    peopleDF.select("name", "age").write.format("csv").save(
           "file:///opt/module/spark-2.1.0/testDir/newpeople.csv")
    // 可以看到/opt/module/spark-2.1.0/testDir这个目录下面有个newpeople.csv文件夹，包含如下两个文件：
    // part-00000-e27e7b21-15e8-43f1-8828-c7cc550a310c.csv 和 _SUCCESS
    // 如果要再次把newpeople.csv中的数据加载到RDD中，可以直接使用newpeople.csv目录名称，
    // 而不需要使用part-r-00000-33184449-cb15-454c-a30f-9bb43faccac1.csv 文件
    val textFile = sc.textFile("file:///opt/module/spark-2.1.0/testDir/newpeople.csv")

    // 2、将DataFrame转换为RDD（弹性分布式数据集）并使用saveAsTextFile()方法将RDD保存到指定路径中
    peopleDF.select("name", "age").write.format("csv").save(
      "file:///opt/module/spark-2.1.0/testDir/newpeople.csv")
    peopleDF.rdd.saveAsTextFile("file:///opt/module/spark-2.1.0/testDir/newpeople.txt")
    // 如果要再次把newpeople.txt中的数据加载到RDD中，可以直接使用newpeople.txt目录名称，而不需要使用part-00000文件
    val textFile = sc.textFile("file://opt/module/spark-2.1.0/testDir/newpeople.txt")
  }
}
