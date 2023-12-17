import org.apache.spark.SparkConf
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * @BelongsProject: test22
 * @BelongsPackage:
 * @CreateTime : 2023/12/17 14:54
 * @Description: test22：SparkStreaming累加计算单词频率
 *               在服务器端不断产生数据的时候，sparkstreaming客户端需要不断统计服务器端产生的相同数据出现的总数，
 *               即累计服务器端产生的相同数据的出现的次数。
 * @Author: code_hlb
 */
object MyNetworkWordCount {
  def main(args: Array[String]): Unit = {
    /**
     * 思路分析
     * 每次客户端程序处理服务器端数据后，将其结果缓存在检查点中，下一次客户端读入数据并处理数据时去检查点
     * 根据key查询和进行更新，并重新将结果更新到检查点中。
     * 检查点：本质上就是对应于HDFS上的一个目录，将数据写入到该目录下以文件的形式将结果保存下来。
     * 因此需要先在hdfs上创建检查点对应的目录。
     */
    //创建一个Context对象: StreamingContext (SparkContext, SQLContext)
    //指定批处理的时间间隔
    val conf = new SparkConf().setAppName("MyNetworkWordCount").setMaster("local[2]")
    val ssc = new StreamingContext(conf, Seconds(5))
    //设置检查点
    ssc.checkpoint("file:///D:\\Code\\scala_code\\test22\\target\\checkpoint")

    //创建一个DStream，处理数据,hadoop001为虚拟机的主机名，端口号为netcat服务的端口号
    val lines = ssc.socketTextStream("hadoop101", 1234, StorageLevel.MEMORY_AND_DISK_SER)
    //执行wordcount
    val words = lines.flatMap(_.split(" "))

    //定义函数用于累计每个单词的总频率
    val addFunc = (currValues: Seq[Int], prevValueState: Option[Int]) => {
      //通过Spark内部的reduceByKey按key规约，然后这里传入某key当前批次的Seq/List,再计算当前批次的总和
      val currentCount = currValues.sum
      // 已累加的值
      val previousCount = prevValueState.getOrElse(0)
      // 返回累加后的结果，是一个Option[Int]类型
      Some(currentCount + previousCount)
    }

    val pairs = words.map(word => (word, 1))

    val totalWordCounts = pairs.updateStateByKey[Int](addFunc)
    totalWordCounts.print()

    ssc.start()
    ssc.awaitTermination()
  }
}
