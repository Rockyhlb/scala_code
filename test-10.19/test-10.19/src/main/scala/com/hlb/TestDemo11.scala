package com.hlb

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author: code_hlb
 * @date : 2023/10/19 22:56
 */
object TestDemo11 {

  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setAppName("writeToFile").setMaster("local")
    val sc = new SparkContext(sparkConf)

    // keyBy
    val rdd1 = sc.parallelize(List("dog", "salmon", "salmon", "rat", "elephant"), 3)
    val rdd2 = rdd1.keyBy(_.length)
    // 输出：Array[(Int, String)] = Array((3,dog), (6,salmon), (6,salmon), (3,rat), (8,elephant))
    rdd2.collect

    // keys values
    val rdd3 = sc.parallelize(List("dog", "tiger", "lion", "cat", "panther", "eagle"), 2)
    val rdd4 = rdd3.map(x => (x.length, x))
    rdd4.keys.collect
    rdd4.values.collect
  }

  def main7(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setAppName("writeToFile").setMaster("local")
    val sc = new SparkContext(sparkConf)

    // foldByKey --> 将RDD[K,V]根据K将V做折叠、合并处理，zeroValue作为初始参数，调用func得到V，
    //再根据Key按照func对V进行调用。
    val rdd1 = sc.parallelize(List("dog", "wolf", "cat", "bear"), 2)
    val rdd2 = rdd1.map(x => (x.length, x))
    val rdd3 = rdd2.foldByKey("")(_+_)
    // 输出：res31: Array[(Int, String)] = Array((4,wolfbear), (3,dogcat))
    rdd3.collect()

    val rdd = sc.textFile("hdfs://hadoop100:9000/input/word.txt").flatMap(_.split(" ")).map((_, 1))
    val rdd4 = rdd.foldByKey(0)(_+_)
    // 输出：res34: Array[(String, Int)] = Array((rocky,2), (mapreduce,1), (hello,1), (yarn,1), (hadoop,2), (world!,1))
    rdd4.collect()
  }

  def main6(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setAppName("writeToFile").setMaster("local")
    val sc = new SparkContext(sparkConf)

    // countByKey --> 计算每个键出现的次数
    val rdd1 = sc.parallelize(List(("a", 1), ("b", 2), ("b", 2), ("c", 2), ("c", 1)))
    rdd1.countByKey
    // countByValue返回每个值的出现次数
    // 输出：res24: scala.collection.Map[(String, Int),Long] = Map((c,2) -> 1, (a,1) -> 1, (b,2) -> 2, (c,1) -> 1)
    rdd1.countByValue

    // filterByRange --> 对rdd中的元素过滤,并返回指定范围的内容数据
    val rdd2 = sc.parallelize(List(("e", 5), ("c", 3), ("d", 4), ("c", 2), ("a", 1)))
    val rdd3 = rdd2.filterByRange("b", "d")
    rdd3.collect

    // flatMapValues --> 主要是对存在元组中的value进行扁平化处理；
    val rdd4 = sc.parallelize(List(("a", "1 2"), ("b", "3 4")))
    val rdd5 = rdd4.flatMapValues(_.split(" "))
    rdd5.collect
  }


  def main5(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setAppName("writeToFile").setMaster("local")
    val sc = new SparkContext(sparkConf)

    /*
      combineByKey与aggregateByKey相比较：
      1.相同点：
          两者都能映射key值分别进行分区内计算和分区间计算。
      2.不同点：
          combineByKey有三个参数列表而且不需要初始值，而aggregateByKey只有两个参数列表且需要初始值。
     */

    //combineByKey方法需要三个参数：
    //第一个参数表示：将相同key的第一个数据进行结构转换，实现操作
    //第二个参数：分区内的计算规则
    //第三个参数：分区间的计算规则
    //    val newRDD: RDD[(String, (Int, Int))] = rdd.combineByKey(
    //      v => (v, 1),
    //      (t: (Int, Int), v) => {
    //        (t._1 + v, t._2 + 1)
    //      },
    //      (t1 Int: , t2: Int) => {
    //      (t1._1 + t2._1, t1._2 + t2._2)
    //    }
    //    )

    //aggregateByKey存在函数颗粒化，有两个参数列表
    //第一个参数列表，需要传递一个参数，表示为初始值
    // 主要当碰见第一个key时候，和value进行分区内计算
    //第二个参数列表，需要传递2个参数
    // 第一个参数表示分区内计算
    // 第二个参数表示分区间计算
//    var rdd = sc.parallelize(List( ("cat",2), ("dog", 12), ("cat", 12), ("cat", 5), ("mouse", 4), ("mouse", 2)), 2)
//
//    rdd.aggregateByKey(zeroValue = 0)(
//      (x, y) => math.max(x, y),
//      (x, y) => x + y
//    ).collect().foreach(println)

    // combineByKey
    val rdd1 = sc.textFile("hdfs://hadoop100:9000/input/word.txt").flatMap(_.split(" ")).map((_, 1))
    val rdd2 = rdd1.combineByKey(x => x, (a: Int, b: Int) => a + b, (m: Int, n: Int) => m + n)
    // 等价于reduceByKey(_ + _), 输出：Array[(String, Int)] = Array[(String, Int)] = Array((rocky,2), (mapreduce,1),
    // (hello,1), (yarn,1), (hadoop,2), (world!,1))
    rdd2.collect

    // 将每个key的value各自加10，输出：Array[(String, Int)]
    val rdd3 = rdd1.combineByKey(x => x + 10, (a: Int, b: Int) => a + b, (m: Int, n: Int) => m + n)
    rdd3.collect

    val rdd4 = sc.parallelize(List("dog","cat","gnu","salmon","rabbit","turkey","wolf","bear","bee"), 3)
    val rdd5 = sc.parallelize(List(1,1,2,2,2,1,2,2,2), 3)
    val rdd6 = rdd5.zip(rdd4)
    rdd6.collect()
  }


  def main4(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setAppName("writeToFile").setMaster("local")
    val sc = new SparkContext(sparkConf)

    /*
      collect、toArray: 将RDD转换为Scala的数组。
      collectAsMap:与collect、toArray相似。collectAsMap将key-value型的RDD转换为Scala的map。
      注意：map中如果有相同的key，其value只保存最后一个值
     */

    var z = sc.parallelize(List( ("cat",2), ("dog", 12), ("cat", 12), ("cat", 5), ("mouse", 4), ("mouse", 2)), 2)
    // 输出所有分区的数据
    z.collect
    // res44: Array[(String, Int)] = Array((cat,2), (dog,12), (cat,12), (cat,5), (mouse,4),  (mouse,2))

    // 转化为字典
    z.collectAsMap
    // res45: scala.collection.Map[String,Int] = Map(dog -> 12, cat -> 5, mouse -> 2)
  }

  def main2(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("writeToFile").setMaster("local")
    val sc = new SparkContext(sparkConf)

    val rdd1 = sc.parallelize(1 to 10,10)
    // 重新分区，分为两个分区，不产生shuffle
    val rdd2 = rdd1.coalesce(2,false)

    // 获取新的RDD分区数
    rdd2.partitions.length
    def func1(index:Int, iter:Iterator[Int]):Iterator[String] = {
      iter.toList.map(x => "[PartID:" + index + ",values=" + x + "]").iterator
    }

    rdd2.mapPartitionsWithIndex(func1).collect()
  }

  def main1(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setAppName("writeToFile").setMaster("local")
    val sc = new SparkContext(sparkConf)

    // checkPoint: 设置检查点
    sc.setCheckpointDir("hdfs://hadoop100:9000/sanGuo")
    val rdd = sc.textFile("hdfs://hadoop100:9000/input/word.txt").flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_)

    rdd.checkpoint()
    rdd.isCheckpointed
    rdd.count()
    rdd.getCheckpointFile
  }
}
