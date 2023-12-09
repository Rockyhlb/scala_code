
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @BelongsProject: test12
 * @BelongsPackage:
 * @CreateTime : 2023/12/9 21:45
 * @Description: spark RDD(弹性分布式数据集) 高级算子使用
 * @Author: code_hlb
 */
object demo12 {
  def main1(args: Array[String]): Unit = {
    /* Map  MapPartitions  MapPartitionsWithIndex
      相同点：
        三个函数的共同点，都是Transformation算子。惰性的算子。
      不同点：
        map函数是一条数据一条数据的处理，也就是，map的输入参数中要包含一条数据以及其他你需要传的参数。
        mapPartitions函数是一个partition数据一起处理，也即是说，mapPartitions函数的输入是一个partition的所有数据构成的“迭代器”，然后函数里面可以一条一条的处理，在把所有结果，按迭代器输出。也可以结合yield使用效果更优。
        mapPartitionsWithIndex函数，其实和mapPartitions函数区别不大，因为mapPartitions背后调的就是mapPartitionsWithIndex函数，只是一个参数被close了。mapPartitionsWithIndex的函数可以获得partition索引号

        假设一个rdd有10个元素，分成3个分区。如果使用map方法，map中的输入函数会被调用10次；
        而使用mapPartitions方法的话，其输入函数会只会被调用3次，每个分区调用1次。
        mapPartitionsWithIndex则是带上分区下标进行操作。
    */
    val sparkConf = new SparkConf().setAppName("RddMapPartitionsWithIndexDemo").setMaster("local")
    val sc = new SparkContext(sparkConf)
    // 接受分区索引和每个分区的迭代器作为参数，并返回一个字符串迭代器，包含了每个元素对应的分区信息
    def getPartInfo: (Int, Iterator[Int]) => Iterator[String] = (index: Int, iter: Iterator[Int]) => {
      iter.map(x => "[ PartId " + index + ", elems: " + x + " ]")
    }
    // mapPartitionWithIndex
    val rdd1 = sc.parallelize(List(1, 2, 3, 4, 5, 9, 6, 7, 8), numSlices = 3)
    val rdd2 = rdd1.mapPartitionsWithIndex(getPartInfo)
    rdd2.collect().foreach(println)
  }

  def main2(args: Array[String]): Unit = {
    // aggregate
    val sparkConf = new SparkConf().setAppName(" RddAggregateDemo").setMaster("local")
    val sc = new SparkContext(sparkConf)
    // (x,y)=>math.min(x.Length,y.length)
    // rdd1.aggregate("")(func1,func2)
    // 首先将初始值“”传入作为x,接着将rdd1的第一个值(12)作为y取最短长度并转化为字符串返回
    // 然后再将结果0作为x,传入第二个值(23)作为y进行比较
    // 由于rdd被分为两个区，因此第三次调用时会将x重新赋为初始值
    // 最终返回0
    def func1: (String, String) => String = (x: String, y: String) => {
      println("<x: " + x + ",x.len: " + x.length + ">,<y: " + y + ",y.len: " + y.length + ">")
      val ret = math.min(x.length, y.length).toString
      println("func1 ret:" + ret)
      ret
    }
    // 与上式等价
    /*    def func1(x:String,y:String) = {
          println("<x: " + x + ",x.len: " + x.length + ">,<y: " + y +",y.len: " + y.length + ">")
          val ret =math.min(x.length,y.length).toString
          println("func1 ret:" +ret)
          ret
        }*/
    //(x+y) => x+y
    // rdd1.aggregate("")(func1,func2)
    // 1、初始值“”为x,将func1的第一个分区的返回值"1"作为y传入
    // 2、x为"1",将func1的第二个分区的返回值"0"作为y传入，拼接结果为"10"
    def func2: (String, String) => String = (x: String, y: String) => {
      println("========" + (x + y))
      x + y
    }
    // rdd1 被分为2个区了，因此第1、3次调用func1和func2时x会被重新赋值为初始值
    val rdd1 = sc.parallelize(List("12", "23", "345", ""), numSlices = 2)
    //下面代码等价于 rdd1.aggregate("")(x,y) => math.min(x.length,y.length)),(x,y)=>x+y))
    val result = rdd1.aggregate("")(func1, func2)
    println(result)
  }

  def main(args: Array[String]): Unit = {
    // aggregateByKey
    val sparkConf = new SparkConf().setAppName("aggregateByKey").setMaster("local")
    val sc = new SparkContext(sparkConf)
    //(x,y)=>math.max(x+y)
    def func1: (Int, Int) => Int = (x: Int, y: Int) => {
      println("<x: " + x + ",y:" + y + ">")
      val ret = math.max(x, y)
      println("func1 max:" + ret)
      ret
    }
    //(x+y) => x+y
    def func2: (Int, Int) => Int = (x: Int, y: Int) => {
      println("========func2 x :" + x + ",y:" + y)
      println("========func2 ret =====" + (x + y))
      x + y
    }
    val pairRDD = sc.parallelize(List(("cat", 2), ("cat", 5), ("mouse", 4), ("cat", 12), ("dog", 12), ("mouse", 2)), numSlices = 2)
    // 第一步：将每个分区内key相同数据放到一起
    //      (“cat”,(2,5)),(“mouse”,4)     (“cat”,12),(“dog”,12),(“mouse”,2)
    // 第二步：局部求最大值(func1)
    //   在分区一中求最大值的时候,100会被加到每个key的值中，这个时候每个分区就会变成下面的样子
    //    (“cat”,(2,5,100)),(“mouse”,(4,100))
    //    (“cat”,(12,100)),(“dog”,(12,100)),(“mouse”,(2,100))
    // 第三步：整体聚合(func2)
    //  将上一步的结果进一步的合成，这个时候100不会再参与进来
    //  输出：(dog,100),(cat,200),(mouse,200)
    val rdd = pairRDD.aggregateByKey(100)(func1, func2)
    val rdd1 = pairRDD.aggregateByKey(100)(func2, func2)
    rdd.collect.foreach(println)
    println("=============== func2  func2 ==============")
    rdd1.collect.foreach(println)
    sc.stop()
  }
}
