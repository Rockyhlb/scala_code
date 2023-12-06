/**
 * @BelongsProject: test11
 * @BelongsPackage:
 * @CreateTime : 2023/12/6 20:40
 * @Description: RDD(弹性数据集)的基本操作
 * @Author: code_hlb
 */
object Test {
  def main(args: Array[String]): Unit = {
    // 以下示例代码都是在spark-shell上运行
    // 练习 1 ：map() sortBy() filter()
    //通过并行化生成 rdd
    val rdd1 = sc.parallelize(List(5, 6, 4, 7, 3, 8, 2, 9, 1, 10))
    //对 rdd1 里的每一个元素乘 2 然后排序
    val rdd2 = rdd1.map(_ * 2).sortBy(x => x, true)
    //过滤出大于等于十的元素
    val rdd3 = rdd2.filter(_ >= 10)
    //将元素以数组的方式在客户端显示
    rdd3.collect

    // 练习 2 ：flatMap() split()
    val rdd1 = sc.parallelize(Array("a b c", "d e f", "h i j"))
    // 将 rdd1 里面的每一个元素先切分在压平
    val rdd2 = rdd1.flatMap(_.split(' '))
    rdd2.collect

    //练习 3 ：并集：union()  交集：intersection()  去重：distinct()
    val rdd1 = sc.parallelize(List(5, 6, 4, 3))
    val rdd2 = sc.parallelize(List(1, 2, 3, 4))
    //求并集
    val rdd3 = rdd1.union(rdd2)
    //求交集
    val rdd4 = rdd1.intersection(rdd2)
    //去重
    rdd3.distinct.collect
    rdd4.collect

    // 练习 4 ：join()  groupByKey()hd
    val rdd1 = sc.parallelize(List(("tom", 1), ("jerry", 3), ("kitty", 2)))
    val rdd2 = sc.parallelize(List(("jerry", 2), ("tom", 1), ("shuke", 2)))
    //求 join
    val rdd3 = rdd1.join(rdd2)
    rdd3.collect
    //求并集
    val rdd4 = rdd1 union rdd2
    //按 key 进行分组
    val rdd5 = rdd4.groupByKey
    rdd5.collect

    //练习 5 ：cogroup()
    val rdd1 = sc.parallelize(List(("tom", 1), ("tom", 2), ("jerry", 3), ("kitty", 2)))
    val rdd2 = sc.parallelize(List(("jerry", 2), ("tom", 1), ("shuke", 2)))
    //cogroup
    val rdd3 = rdd1.cogroup(rdd2)
    rdd3.collect
    /* 注意 cogroup 与 groupByKey 的区别
    groupByKey:
     groupByKey是对单个RDD的数据进行分组，,groupByKey会将RDD[key, value] 按照相同的key进行分组，
     形成RDD[key, iterable[value]] 的形式，有点类似于sql中的groupBy.

    cogroup:
     cogroup() 函数对多个共享同一个键的RDD进行分组, 例：RDD1.cogroup(RDD2) 会将RDD1和RDD2按照相同的key进行分组，
     可以得到(key, RDD[key, Iterable[value1], Iterable[value2]]) 的形式.
     cogroup也可以多个进行分组,例：RDD1.cogroup(RDD2, RDD3,…,RDDN) ，
     可以得到(key, Iterable[value1], Iterable[value2], Iterable[value3],…, Iterable[valueN])
    */

    // 练习 6 ：reduce(),默认为 reduceLeft()
    val rdd1 = sc.parallelize(List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
    //reduce 聚合
    val rdd2 = rdd1.reduce(_ + _)
    //rdd2.collect

    // 练习 7 ：reduceByKey(), sort
    val rdd1 = sc.parallelize(List(("tom", 1), ("jerry", 3), ("kitty", 2), ("shuke", 1)))
    val rdd2 = sc.parallelize(List(("jerry", 2), ("tom", 3), ("shuke", 2), ("kitty", 5)))
    val rdd3 = rdd1.union(rdd2)
    //按 key 进行聚合
    val rdd4 = rdd3.reduceByKey(_ + _)
    rdd4.collect
    //按 value 的降序排序
    val rdd5 = rdd4.map(t => (t._2, t._1)).sortByKey(false).map(t => (t._2, t._1))
    rdd5.collect
  }
}
