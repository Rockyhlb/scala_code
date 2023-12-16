import org.apache.spark.SparkConf
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * @BelongsProject: test21
 * @BelongsPackage:
 * @CreateTime : 2023/12/16 9:54
 * @Description: test21※ Spark Streaming入门实验(套接字流数据源)：
 *               将nc作为服务器端，用户产生数据；启动sparkstreaming案例中的客户端程序，
 *               监听服务器端发送过来的数据，并对其数据进行词频统计，即为流式的wordcount入门程序
 *               缺陷：只能统计最新一次输入的词频，不能对历史的统计数量进行更新，对此处的优化在下一个实验当中
 * @Author: code_hlb
 */
object NetWordCount {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[2]").setAppName("StreamingTest")
    val streamingContext = new StreamingContext(sparkConf, Seconds(5))
    // 先在虚拟机上启动nc服务：nc -l 1234，并输入测试数据
    // 创建DStream对象，并链接到nc服务器端
    val ris: ReceiverInputDStream[String] = streamingContext.socketTextStream("hadoop101", 1234, StorageLevel.MEMORY_AND_DISK)
    // DStream(Discretized Stream，离散化数据流），表示连续不断的数据流。
    // 采集数据，并处理数据
    val ds: DStream[String] = ris.flatMap(_.split(" "))
    println(ris)
    // 统计单词
    val resultDS: DStream[(String, Int)] = ds.map(x => (x, 1)).reduceByKey(_ + _)
    // 打印结果
    resultDS.print()
    // 启动实时计算
    streamingContext.start()
    // 等待计算结束
    streamingContext.awaitTermination()
  }
}
