package com.hlb.process

import java.sql.{Connection, DriverManager, PreparedStatement}
import com.hlb.util.OffsetUtils
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.TopicPartition
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.kafka010._
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable

/**
 * @BelongsProject: test33
 * @BelongsPackage: com.hlb.process
 * @CreateTime : 2023/12/28 9:53
 * @Description: 消费Kafka数据并将数据存入MySQL中
 * @Author: code_hlb
 */
object Job_ClickData_Process {
  // 数据实时处理分析
  def main(args: Array[String]): Unit = {
    //1.准备SparkStreaming的开发环境
    val conf = new SparkConf().setAppName("Job_ClickData_Process").setMaster("local[2]")
    val context = new SparkContext(conf)
    context.setLogLevel("WARN")
    val ssc = new StreamingContext(context, Seconds(5)) //连续流批次处理的大小
    ssc.checkpoint("./ssckp")

    //2.准备kafka的连接参数
    val kafkaParams: Map[String, Object] = Map[String, Object](
      "bootstrap.servers" -> "hadoop101:9092",
      "group.id" -> "SparkKafka",
      //latest表示如果记录了偏移量的位置，就从记录的位置开始消费，如果没有记录，就从最新/或最后的位置开始消费
      //earliest表示如果记录了偏移量的位置，就从记录的位置开始消费，如果没有记录，就从最开始/最早的位置开始消费
      //none示如果记录了偏移量的位置，就从记录的位置开始消费，如果没有记录，则报错
      "auto.offset.reset" -> "latest", //偏移量的重置位置
      "enable.auto.commit" -> (false: java.lang.Boolean), //是否自动提交偏移量
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer]
    )
    val topics: Array[String] = Array("RealDataTopic")

    //从mysql中查询出offsets:Map[TopicPartition, Long]
    val offsetsMap: mutable.Map[TopicPartition, Long] = OffsetUtils.getOffsetMap(
      "SparkKafka", "RealDataTopic")
    val kafkaDS: InputDStream[ConsumerRecord[String, String]] = if (offsetsMap.size > 0) {
      println("MySql记录了offset信息,从offset处开始消费")
      //3.连接kafka的消息
      KafkaUtils.createDirectStream[String, String](
        ssc,
        LocationStrategies.PreferConsistent,
        ConsumerStrategies.Subscribe[String, String](topics, kafkaParams, offsetsMap)
      )
    } else {
      println("MySql没有记录offset信息,从latest处开始消费")
      //3.连接kafka的消息
      KafkaUtils.createDirectStream[String, String](
        ssc,
        LocationStrategies.PreferConsistent,
        ConsumerStrategies.Subscribe[String, String](topics, kafkaParams)
      )
    }

    //4.实时处理数据并手动维护offset
    val valueDS = kafkaDS.map(_.value()) //_表示从kafka中消费出来的每一条数据
    valueDS.print()
    kafkaDS.map(_.value())
    valueDS.foreachRDD(rdd => {
      rdd.foreachPartition(lines => {
        //1.开启连接
        val conn: Connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" +
          "bigdata?characterEncoding=UTF-8&serverTimezone=UTC", "root", "000000")
        //2.编写sql并获取ps
        val sql: String = "replace into job_real_time(datetime,job_type,job_id,count) values(?,?,?,?)"
        val ps: PreparedStatement = conn.prepareStatement(sql)
        //3.设置参数并执行
        for (line <- lines) {
          var item = line.split(" ")
          ps.setString(1, item(0).toString)
          ps.setInt(2, item(1).toInt)
          ps.setInt(3, item(2).toInt)
          ps.setInt(4, item(3).toInt)
          ps.executeUpdate()
        }
        //4.关闭资源
        ps.close()
        conn.close()
      })
    })
    //5.将处理分析的结果存入mysql
    /*
    在本地数据库创建 `job_real_time`
    DROP TABLE IF EXISTS `job_real_time`;
    CREATE TABLE `job_real_time` (
    `datetime` varchar(8) DEFAULT NULL COMMENT '日期',
    `job_type` int(2) DEFAULT NULL COMMENT '1代表新招聘岗位，0代表找工作的人',
    `job_id` int(8) DEFAULT NULL COMMENT '岗位ID，匹配岗位名称',
    `count` int(8) DEFAULT NULL COMMENT '企业新增岗位数和找工作的人数'
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
    */

    //6.手动提交偏移量
    kafkaDS.foreachRDD(rdd => {
      if (rdd.count() > 0) {
        //rdd.foreach(record=>println("从kafka中消费到的每一条数据："+record))
        //从kafka中消费到的每一条数据：ConsumerRecord(topic = job_click, partition = 0, offset = 14,
        // CreateTime = 1664438857755, checksum = 207199849, serialized key size = -1, serialized value size = 3,
        // key = null, value = aaa)

        //获取偏移量
        val offsets: Array[OffsetRange] = rdd.asInstanceOf[HasOffsetRanges].offsetRanges
        //for(o <-offsets){
        //println(s"topic=${o.topic},partition=${o.partition},fromOffset=${o.fromOffset},until=${o.untilOffset}")
        // topic=job_click,partition=1,fromOffset=14,until=16
        // topic=job_click,partition=0,fromOffset=18,until=19
        //}
        //手动提交offset到kafka集群的默认主题__consumer_offsets如果开起来checkpoint，还会提交到checkpoint中
        //kafkaDS.asInstanceOf[CanCommitOffsets].commitAsync(offsets)
        OffsetUtils.saveOffsets(groupId = "SparkKafka", offsets)
      }
    })

    //7.开启sparkstreaming任务并等待结束
    ssc.start()
    ssc.awaitTermination()
  }
}
