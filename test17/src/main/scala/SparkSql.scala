
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @BelongsProject: test17
 * @BelongsPackage:
 * @CreateTime : 2023/12/14 16:15
 * @Description: SparkSql: 定义法创建Dataframe并使用DSL操作数据
 * @Author: code_hlb
 */
object SparkSql {
  def main(args: Array[String]): Unit = {
    /*val sc = new SparkContext(new SparkConf().setAppName("demo").setMaster("local[2]"))
    val spark = SparkSession.builder().getOrCreate();
    // 一.共性：
    //1.RDD、 DataFrame、DataSet都是spark平台下的分布式数据集，为处理超大型数据提供便利；
    //2.三者都有惰性机制，在进行创建、转换时，不会立即执行，只有在遇到行动算子的时候才会开始计算；
    //3.在对DataFrame和DataSet进行操作时，许多操作都需要导入：import spark.implicits._ 包；
    //4.三者都会根据Spark的内存情况进行自动缓存计算，这样即使数据量很大，也不会担心内存溢出；
    //
    //二.区别：
    // RDD
    //  RDD不支持sparksql操作
    // DataFrame:
    //1、与RDD和Dataset不同，DataFrame每一行的类型固定为Row，只有通过解析才能获取各个字段的值，如
    testDF.foreach{ line =>
      val col1=line.getAs[String]("col1")
      val col2=line.getAs[String]("col2")
    }
    //2、DataFrame与Dataset均支持sparksql的操作，比如select，groupby之类，还能注册临时表/视图后进行sql语句操作，如
    dataDF.createOrReplaceTempView("tmp")
    spark.sql("select ROW,DATE from tmp where DATE is not null order by DATE").show
    //Dataset:
    // 这里主要对比Dataset和DataFrame，因为Dataset和DataFrame拥有完全相同的成员函数，区别只是每一行的数据类型不同
    // DataFrame也可以叫Dataset[Row],每一行的类型是Row，不解析，每一行究竟有哪些字段，各个字段又是什么类型都无从得知，
    // 只能getAS方法或者模式匹配拿出特定字段
    // 而Dataset中，每一行是什么类型是不一定的，在自定义了case class之后可以很自由的获得每一行的信息
    case class Coltest(col1:String,col2:Int) extends Serializable //定义字段名和类型
    /**
    rdd
    ("a", 1)
    ("b", 1)
    ("a", 1)
    */
    val test: Dataset[Coltest]=rdd.map{ line=>Coltest(line._1,line._2)}.toDS
    test.map{ line=>
      println(line.col1)
      println(line.col2)
    }
    // 可以看出，Dataset在需要访问列中的某个字段时是非常方便的，然而，如果要写一些适配性很强的函数时，
    // 如果使用Dataset，行的类型又不确定，可能是各种case class，无法实现适配，
    // 这时候用DataFrame即Dataset[Row]就能比较好的解决问题
    //转化：
    //RDD、DataFrame、Dataset三者有许多共性，有各自适用的场景常常需要在三者之间转换
    //DataFrame/Dataset转RDD：
    val rdd1=testDF.rdd
    val rdd2=testDS.rdd
    //RDD转DataFrame：
    //一般用元组把一行的数据写在一起，然后在toDF中指定字段名
    import spark.implicits._
    val testDF = rdd.map { line=>(line._1,line._2)}.toDF("col1","col2")
    //RDD转Dataset：
    import spark.implicits._
    case class Coltest(col1:String,col2:Int)extends Serializable //定义字段名和类型
    val testDS = rdd.map {line=>Coltest(line._1,line._2)}.toDS
    //可以注意到，定义每一行的类型（case class）时，已经给出了字段名和类型，后面只要往case class里面添加值即可
    //Dataset转DataFrame：
    // 只是把case class封装成Row
    import spark.implicits._
    val testDF = testDS.toDF
    //DataFrame转Dataset：
    import spark.implicits._
    case class Coltest(col1:String,col2:Int) extends Serializable //定义字段名和类型
    // 这种方法就是在给出每一列的类型后，使用as方法，转成Dataset，这在数据类型是DataFrame又需要针对各个字段处理时极为方便
    val testDS = testDF.as[Coltest]*/
  }
}
