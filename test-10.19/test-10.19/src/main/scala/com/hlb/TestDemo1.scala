package com.hlb

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author: code_hlb
 * @date : 2023/10/20 16:40
 */
object TestDemo1 {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("BroadCastValue1").setMaster("local")
    val sc = new SparkContext(conf)

    // 共享变量  -->  累加器
    /*累加器是仅仅被相关操作累加的变量，通常可以被用来实现计数器（counter）和求和（sum），
    一个数值型的累加器，可以通过调用SparkContext.longAccumulator()或者SparkContext.doubleAccumulator()来创建。
    运行在集群中的任务，就可以使用add方法来把数值累加到累加器上，但是，这些任务只能做累加操作，不能读取累加器的值，
    只有任务控制节点（Driver Program）可以使用value方法来读取累加器的值*/

    val accum = sc.longAccumulator("My Accumulator")
    // accum: org.apache.spark.util.LongAccumulator = LongAccumulator(id: 0, name: Some(My Accumulator), value: 0)
    sc.parallelize(Array(1, 2, 3, 4)).foreach(x => accum.add(x))
    // 输出：res1: Long = 10
    accum.value
  }

  def main2(args: Array[String]): Unit = {
    // 共享变量  -->  广播变量
    // 广播变量被创建以后，那么在集群中的任何函数中，都应该使用广播变量broadcastVar的值，而不是使用v的值，这样就不会把v重复分发到这些节点上
    //此外，一旦广播变量创建后，普通变量v的值就不能再发生修改，确保所有节点都获得这个广播变量的相同的值
    val conf = new SparkConf().setAppName("BroadCastValue1").setMaster("local")
    // 获取SparkContext
    val sc = new SparkContext(conf)
    // 创建广播变量
    val v = 3
    val broads = sc.broadcast(v)
    // 变量可以是任意类型
    // 创建一个测试的List
    val lists = List(1, 2, 3, 4, 5)
    //转换为rdd（并行化）
    val listRDD = sc.parallelize(lists)
    //map操作数据
    val results = listRDD.map(x => x * broads.value)
    //遍历结果
    results.foreach(x => println("The result is: " + x))
    sc.stop
  }


  def main1(args: Array[String]): Unit = {

    /*题目：给定一组键值对("spark",2),("hadoop",6),("hadoop",4),("spark",6)，
  键值对的key表示图书名称，value表示某天图书销量，请计算每个键对应的平均值，
  也就是计算每种图书的每天平均销量。*/

    val sparkConf = new SparkConf().setAppName("writeToFile").setMaster("local")
    val sc = new SparkContext(sparkConf)

    val pairRdd = sc.parallelize(Array(("spark",2),("hadoop",6),("hadoop",4),("spark",6)))
    //  mapValues(x => (x,1))  -->  ("spark",(2,1))
    //  reduceByKey((x,y) => (x._1+y._1,x._2+y._2))  -->   ("spark",(8,2))  ("hadoop",(10,2))
    //  mapValues(x => (x._1/x._2))   -->  ("spark",4)  ("hadoop",5)
    pairRdd.mapValues(x => (x,1)).reduceByKey((x,y) => (x._1+y._1,x._2+y._2)).mapValues(x => (x._1/x._2)).collect()
  }
}
