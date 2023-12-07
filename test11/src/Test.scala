/**
 * @BelongsProject: test11
 * @BelongsPackage:
 * @CreateTime : 2023/12/6 21:59
 * @Description: 高级算子
 * @Author: code_hlb
 */
object Test {
  def main(args: Array[String]): Unit = {
    /*spark的rdd编程中有两个算子repartition和coalesce。两者都是对spark分区数进行调整的算子。
      repartition会经过shuffle，其实际上就是调用的coalesce(shuffle=true)。

      coalesce，默认shuffle=false，不会经过shuffle。
      可以认为是：如果你想要从1000个分区到100个分区，并且不经过shuffle，近乎平均分配10个父分区到1个子分区。

      不经过shuffle，就意味着coalesce算子前后都是在一个stage中的。因此从该stage开始到coalesce算子之前
      的任务的迭代执行的并行度都是1000，从coalesce算子开始到该stage结束的任务的迭代执行的并行度都是100。*/
    val rdd1 = sc.parallelize(1 to 10, 10)
    // 重新分区，分为两个2 ，不产生shuffle
    val rdd2 = rdd1.coalesce(2, false)

    // 获取新的RDD分区数   输出：2
    rdd2.partitions.length

    def func1(index: Int, iter: Iterator[Int]): Iterator[String] = {
      iter.toList.map(x => "[PartID:" + index + ",value=" + x + "]").iterator
    }
    // 查看分区后的结果：
    // 输出： Array[String] = Array([PartID:0,value=1], [PartID:0,value=2], [PartID:0,value=3],
    // [PartID:0,value=4], [PartID:0,value=5], [PartID:1,value=6],
    // [PartID:1,value=7], [PartID:1,value=8], [PartID:1,value=9], [PartID:1,value=10])
    rdd2.mapPartitionsWithIndex(func1).collect

    //repartition:
    val rdd1 = sc.parallelize(1 to 10, 4)
    val rdd2 = rdd1.repartition(5)

    /*collect、toArray将RDD转换为Scala的数组。
    collectAsMap 与 collect、toArray相似。collectAsMap将key - value型的RDD转换为Scala的map。
    注意：map中如果有相同的key，其value只保存最后一个值。*/
    //创建一个2分区的RDD
    var z = sc.parallelize(List(("cat", 2), ("cat", 5), ("mouse", 4), ("cat", 12),
                          ("dog", 12), ("mouse", 2)), 2)
    //输出所有分区的数据
    z.collect
    //转化为字典
    // res5: scala.collection.Map[String,Int] = Map(dog -> 12, cat -> 12, mouse -> 2)
    z.collectAsMap

    /*combineByKey与aggregateByKey相比较：
    1.相同点：两者都能映射key值分别进行分区内计算和分区间计算。
    2. 不同点：combineByKey有三个参数列表而且不需要初始值，而aggregateByKey只有两个参数列表且需要初始值.
    combineByKey与aggregateByKey两者的核心区别 ，就是在组内初始计算时少许不同。*/

    combineByKey // 是在这个PairRDDFunctions类下的方法
    val rdd1 = sc.textFile("hdfs://Master:9000/input/word.txt").flatMap(_.split(" ")).map((_, 1))
    // 等价于reduceByKey(_ + _),结果：Array[(String, Int)] = Array((is, 1), (Guiyang, 1), (love, 2), (capital, 1), (Guiyang, 1), (I, 2), (of, 1), (Guizhou, 2), (the, 1))
    val rdd2 = rdd1.combineByKey(x => x, (a: Int, b: Int) => a + b, (m: Int, n: Int) => m + n)
    rdd2.collect
    //将每个key的value各自加10，结果：Array[(String, Int)]
    val rdd3 = rdd1.combineByKey(x => x + 10, (a: Int, b: Int) => a + b, (m: Int, n: Int) => m + n)

    rdd3.collect
    val rdd4 = sc.parallelize(List("dog", "cat", "gnu", "salmon", "rabbit", "turkey", "wolf", "bear", "bee"), 3)
    val rdd5 = sc.parallelize(List(1, 1, 2, 2, 2, 1, 2, 2, 2), 3)
    val rdd6 = rdd5.zip(rdd4)

    /*一、Rdd行动算子
    1、【countByKey】统计存储在rdd中元组的key的个数，key是相同的就会进行计数 + 1 。
    通过这个key 会生成一个Map Map中的key是原有中的key, value是原有key的个数；
    2、【countByValue】统计在Rdd中存储元素的个数。会将rdd中每一个元组看作为一个value,
    若这个元组中元素是相同的，此时就会将生成Map中的value + 1；
    3、【filterByRange】对rdd中的元素过滤，并返回指定内容的数据。该函数作用于键值对RDD，
    对RDD中的元素进行过滤，返回键在指定范围中的元素；
    4、【flatMapValues】主要是对存在元组中的value进行扁平化处理；*/

    // countByKey: 计算每个键出现的次数
    val rdd1 = sc.parallelize(List(("a", 1), ("b", 2), ("b", 2), ("c", 2), ("c", 1)))
    rdd1.countByKey
    rdd1.countByValue // countByValue返回每个值的出现次数

    // filterByRange:对rdd中的元素过滤,并返回指定范围的内容数据
    val rdd1 = sc.parallelize(List(("e", 5), ("c", 3), ("d", 4), ("c", 2), ("a", 1)))
    val rdd2 = rdd1.filterByRange("b", "d")
    rdd2.collect

    // flatMapValues
    val rdd3 = sc.parallelize(List(("a", "1 2"), ("b", "3 4")))
    rdd3.flatMapValues(_.split(" "))
    rdd3.collect

    // foldByKey
    //作用：将RDD[K, V] 根据K将V做折叠、合并处理，zeroValue作为初始参数，调用func得到V，
    //再根据Key按照func对V进行调用。
    var rdd1 = sc.makeRDD(Array(("A", 0), ("A", 2), ("B", 1), ("B", 2)))
    rdd1.foldByKey(0)(_+_).collect
    // res3: Array[(String, Int)] = Array((A,2), (B,3))
    说明： 将0应用到_+_上，Array(("A",0+0),("A",2+0)) 再进一步处理得到Array(("A",0+2))最终得到Array(("A",2))

    // foldByKey
    val rdd1 = sc.parallelize(List("dog", "wolf", "cat", "bear"), 2)
    val rdd2 = rdd1.map(x => (x.length, x))
    val rdd3 = rdd2.foldByKey("")(_+_)
    val rdd = sc.textFile("hdfs://Master:9000/input/word.txt").flatMap(_.split(" ")).map((_, 1))
    rdd.foldByKey(0)(_+_)

    // foreachPartition: 是spark-core的action算子,该算子源码中的注释是:Applies a function func to each parition of this RDD.(将函数func应用于此RDD的每个分区)
    val rdd1 = sc.parallelize(List(1, 2, 3, 4, 5, 6, 7, 8, 9), 3)
    rdd1.foreachPartition(x => println(x.reduce(_ + _)))

    // keyBy
    val rdd1 = sc.parallelize(List("dog", "salmon", "salmon", "rat", "elephant"), 3)
    val rdd2 = rdd1.keyBy(_.length)
    rdd2.collect

    // keys values
    val rdd1 = sc.parallelize(List("dog", "tiger", "lion", "cat", "panther", "eagle"), 2)
    val rdd2 = rdd1.map(x => (x.length, x))
    rdd2.keys.collect
    rdd2.values.collect
  }
}
