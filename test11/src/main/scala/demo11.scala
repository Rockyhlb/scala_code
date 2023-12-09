
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @BelongsProject: test11
 * @BelongsPackage:
 * @CreateTime : 2023/12/9 22:45
 * @Description: spark RDD(弹性分布式数据集) 高级算子操作
 * @Author: code_hlb
 */
object demo11 {

  def main1(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("writeToFile").setMaster("local")
    val sc = new SparkContext(sparkConf)
    // checkPoint: 设置检查点
    sc.setCheckpointDir("hdfs://hadoop100:9000/sanGuo/checkPoint")
    val rdd = sc.textFile("hdfs://hadoop100:9000/input/word.txt").flatMap(_.split(" ")).map((_, 1))
                 .reduceByKey(_ + _)
    rdd.checkpoint()
    rdd.isCheckpointed
    rdd.count()
    rdd.getCheckpointFile
  }

  def main2(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("writeToFile").setMaster("local")
    val sc = new SparkContext(sparkConf)
    /**
     * spark的rdd编程中有两个算子repartition和coalesce。两者都是对spark分区数进行调整的算子。
     * repartition会经过shuffle，其实际上就是调用的coalesce(shuffle=true)。
     * coalesce，默认shuffle=false，不会经过shuffle，可以认为是：如果你想要从1000个分区到100个分区，
     * 并且不经过shuffle，近乎平均分配10个父分区到1个子分区。
     * 不经过shuffle，就意味着coalesce算子前后都是在一个stage中的。因此从该stage开始到coalesce算子之前
     * 的任务的迭代执行的并行度都是1000，从coalesce算子开始到该stage结束的任务的迭代执行的并行度都是100。
     */
    val rdd1 = sc.parallelize(1 to 10, 10)
    rdd2.partitions.length  // 输出：10
    // 重新分区，分为两个分区，不产生shuffle
    val rdd2 = rdd1.coalesce(2, false)
    // 获取新的RDD分区数    输出：2
    rdd2.partitions.length

    def func1(index: Int, iter: Iterator[Int]): Iterator[String] = {
      iter.toList.map(x => "[PartID:" + index + ",values=" + x + "]").iterator
    }
    // 查看分区后的结果：
    // 输出： Array[String] = Array([PartID:0,value=1], [PartID:0,value=2], [PartID:0,value=3],
    // [PartID:0,value=4], [PartID:0,value=5], [PartID:1,value=6],
    // [PartID:1,value=7], [PartID:1,value=8], [PartID:1,value=9], [PartID:1,value=10])
    rdd2.mapPartitionsWithIndex(func1).collect()

    // repartition:
    val rdd1 = sc.parallelize(1 to 10, 4)
    val rdd2 = rdd1.repartition(5)
    rdd2.partitions.length
  }

  def main3(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("writeToFile").setMaster("local")
    val sc = new SparkContext(sparkConf)
    /**
     * collect、toArray: 将RDD转换为Scala的数组。
     * collectAsMap:与collect、toArray相似。collectAsMap将key-value型的RDD转换为Scala的map。
     * 注意：map中如果有相同的key，其value只保存最后一个值
     */
    //创建一个分区为 2 的RDD
    var z = sc.parallelize(List(("cat", 2), ("dog", 12), ("cat", 12), ("cat", 5),
      ("mouse", 4), ("mouse", 2)), 2)
    // 输出所有分区的数据
    // res44: Array[(String, Int)] = Array((cat,2), (dog,12), (cat,12), (cat,5), (mouse,4),  (mouse,2))
    z.collect

    // 转化为字典，value只保存最后一个值
    // res45: scala.collection.Map[String,Int] = Map(dog -> 12, cat -> 5, mouse -> 2)
    z.collectAsMap
  }

  def main5(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("writeToFile").setMaster("local")
    val sc = new SparkContext(sparkConf)
    /**
     * combineByKey与aggregateByKey相比较：
     * 1.相同点：
     * 两者都能映射key值分别进行分区内计算和分区间计算。
     * 2.不同点：
     * combineByKey有三个参数列表而且不需要初始值，而aggregateByKey只有两个参数列表且需要初始值。
     * combineByKey与aggregateByKey两者的核心区别 ，就是在组内初始计算时少许不同。
     */
    // combineByKey --> 是在 PairRDDFunctions 这个类下的方法
    val rdd1 = sc.textFile("hdfs://hadoop100:9000/input/word.txt").flatMap(_.split(" ")).map((_, 1))
    val rdd2 = rdd1.combineByKey(x => x, (a: Int, b: Int) => a + b, (m: Int, n: Int) => m + n)
    // 等价于reduceByKey(_ + _), 输出：Array[(String, Int)] = Array[(String, Int)] = Array((rocky,2), (mapreduce,1),
    // (hello,1), (yarn,1), (hadoop,2), (world!,1))
    rdd2.collect

    // 将每个key的value各自加10，输出：Array[(String, Int)]
    val rdd3 = rdd1.combineByKey(x => x + 10, (a: Int, b: Int) => a + b, (m: Int, n: Int) => m + n)
    rdd3.collect

    // 输出：Array[(String, Int)] = Array((rocky,100), (mapreduce,100), (hello,100),
    // (yarn,100), (hadoop,100), (world!,100))
    val pairRdd = rdd1.aggregateByKey(100)(math.max(_ , _), _ + _ ).collect

    val rdd4 = sc.parallelize(List("dog","cat","gnu","salmon","rabbit","turkey", "wolf", "bear", "bee"), 3)
    val rdd5 = sc.parallelize(List(1, 1, 2, 2, 2, 1, 2, 2, 2), 3)
    /**
     * 在 Apache Spark 中，zip 是一个用于将两个 RDD（弹性分布式数据集）进行元素级别的配对操作的函数。
     * zip 操作将两个 RDD 中相同位置的元素合并成一个键值对，其中第一个 RDD 的元素作为键，第二个 RDD 的元素作为值。
     * 使用 zip 操作需要满足以下条件：
     * 1、两个 RDD 必须具有相同的分区数
     * 2、两个 RDD 中的分区内元素数量必须相等
     */
    val rdd6 = rdd5.zip(rdd4)
    // 输出：res0: Array[(Int, String)] = Array((1,dog), (1,cat), (2,gnu), (2,salmon), (2,rabbit),
    // (1,turkey), (2,wolf), (2,bear), (2,bee))
    rdd6.collect()
  }

  def main6(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("writeToFile").setMaster("local")
    val sc = new SparkContext(sparkConf)
    /**
     * 一、Rdd行动算子
     * 1、【countByKey】统计存储在rdd中元组的key的个数，key是相同的就会进行计数 + 1 。
     * 通过这个key 会生成一个Map Map中的key是原有中的key, value是原有key的个数；
     * 2、【countByValue】统计在Rdd中存储元素的个数。会将rdd中每一个元组看作为一个value,
     * 若这个元组中元素是相同的，此时就会将生成Map中的value + 1；
     * 3、【filterByRange】对rdd中的元素过滤，并返回指定内容的数据。该函数作用于键值对RDD，
     * 对RDD中的元素进行过滤，返回键在指定范围中的元素；
     * 4、【flatMapValues】主要是对存在元组中的value进行扁平化处理；
     */
    // countByKey --> 计算每个键出现的次数
    val rdd1 = sc.parallelize(List(("a", 1), ("b", 2), ("b", 2), ("c", 2), ("c", 1)))
    // 输出： Map(a -> 1, b -> 2, c -> 2)
    rdd1.countByKey
    // countByValue返回每个值的出现次数
    // 输出：res24: scala.collection.Map[(String, Int),Long] = Map((c,2) -> 1, (a,1) -> 1, (b,2) -> 2, (c,1) -> 1)
    rdd1.countByValue // countByValue返回每个值的出现次数

    // filterByRange --> 对rdd中的元素过滤,并返回指定范围的内容数据
    val rdd2 = sc.parallelize(List(("e", 5), ("c", 3), ("d", 4), ("c", 2), ("a", 1)))
    val rdd3 = rdd2.filterByRange("b", "d")   // 闭区间
    // 输出：Array[(String, Int)] = Array((c,3), (d,4), (c,2))
    rdd3.collect

    // flatMapValues --> 主要是对存在元组中的value进行扁平化处理；
    val rdd4 = sc.parallelize(List(("a", "1 2"), ("b", "3 4")))
    val rdd5 = rdd4.flatMapValues(_.split(" "))
    rdd5.collect
  }

  def main7(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("writeToFile").setMaster("local")
    val sc = new SparkContext(sparkConf)
    // foldByKey --> 将RDD[K,V]根据K将V做折叠、合并处理，zeroValue作为初始参数，调用func得到V，
    // 再根据Key按照func对V进行调用。
    val rdd1 = sc.parallelize(List("dog", "wolf", "cat", "bear"), 2)
    val rdd2 = rdd1.map(x => (x.length, x))
    val rdd3 = rdd2.foldByKey("")(_ + _)
    // 输出：Array[(Int, String)] = Array((4,wolfbear), (3,dogcat))
    rdd3.collect()

    val rdd = sc.textFile("hdfs://hadoop100:9000/input/word.txt").flatMap(_.split(" ")).map((_, 1))
    val rdd4 = rdd.foldByKey(0)(_ + _)
    // 输出：res34: Array[(String, Int)] = Array((rocky,2), (mapreduce,1), (hello,1), (yarn,1), (hadoop,2), (world!,1))
    rdd4.collect()
  }

  def main8(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("writeToFile").setMaster("local")
    val sc = new SparkContext(sparkConf)
    // foreachPartition: 是spark-core的action算子,
    // 该算子源码中的注释是:Applies a function func to each parition of this RDD. (将函数func应用于此RDD的每个分区)
    val rdd1 = sc.parallelize(List(1, 2, 3, 4, 5, 6, 7, 8, 9), 3)
    // 输出： 6\n  15\n  24
    rdd1.foreachPartition(x => println(x.reduce(_ + _)))

    // keyBy
    val rdd1 = sc.parallelize(List("dog", "salmon", "salmon", "rat", "elephant"), 3)
    val rdd2 = rdd1.keyBy(_.length)
    // 输出：Array[(Int, String)] = Array((3,dog), (6,salmon), (6,salmon), (3,rat), (8,elephant))
    rdd2.collect

    // keys values
    val rdd3 = sc.parallelize(List("dog", "tiger", "lion", "cat", "panther", "eagle"), 2)
    val rdd4 = rdd3.map(x => (x.length, x))
    // 输出：Array[(Int, String)] = Array((3,dog), (5,tiger), (4,lion), (3,cat), (7,panther), (5,eagle))
    rdd4.collect;
    // 输出： Array[Int] = Array(3, 5, 4, 3, 7, 5)
    rdd4.keys.collect
    // 输出：Array[String] = Array(dog, tiger, lion, cat, panther, eagle)
    rdd4.values.collect
  }
}
