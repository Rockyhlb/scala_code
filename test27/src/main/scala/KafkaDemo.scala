import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.kafka010._
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe

/**
 * @BelongsProject: test27
 * @BelongsPackage:
 * @CreateTime : 2023/12/18 10:54
 * @Description: test27：SparkStreaming读取Kafka数据源：使用Direct方式
 * @Author: code_hlb
 */
object  KafkaDemo {
  def main(args: Array[String]): Unit = {
    /**
     * 一、启动环境
     * 启动zookeeper(集群的每一台都需要启动zookeeper)，
     * /opt/module/zookeeper-3.5.7/bin/zkServer.sh start
     * 启动Kafka
     * /opt/module/kafka-2.3.1/bin/kafka-server-start.sh /opt/module/kafka-2.3.1/config/ser
     *  ver.properties
     * 启动Kafka控制台生产者
     * /opt/module/kafka-2.3.1/bin/kafka-console-producer.sh --broker-list hadoop101:9092
     *  --topic test1
     */
    val sparkConf = new SparkConf().setMaster("local[2]").setAppName("KafkaTest")
    val streamingContext = new StreamingContext(sparkConf, Seconds(5))

    val kafkaParams = Map[String, Object](
      "bootstrap.servers" -> "hadoop101:9092", //kafka列表
      "key.deserializer" -> classOf[StringDeserializer], ////k和v 的序列化类型
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> "use_a_separate_group_id_for_each_stream", //消费者组
      "auto.offset.reset" -> "latest", //如果没有记录偏移量，第一次从最开始读，有偏移量，接着偏移量读
      "enable.auto.commit" -> (false: java.lang.Boolean) // 消费者不自动提交偏移量
    )

    val topics = Array("test1", "t100")
    // createDirectStream: 主动拉取数据
    val stream = KafkaUtils.createDirectStream[String, String](
      streamingContext,
      PreferConsistent,
      Subscribe[String, String](topics, kafkaParams)
    )

    val mapDStream: DStream[(String, String)] = stream.map(record => (record.key, record.value))
    //kafka 是一个key value 格式的， 默认key 为null ,一般用不上
    val resultRDD: DStream[(String, Int)] = mapDStream.flatMap(_._2.split(" "))
                                            .map((_, 1)).reduceByKey(_ + _)
    // 打印
    resultRDD.print()
    // 启动
    streamingContext.start()
    // 等待计算结束
    streamingContext.awaitTermination()
  }
}
