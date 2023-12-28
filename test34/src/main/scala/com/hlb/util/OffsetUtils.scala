package com.hlb.util

import org.apache.kafka.common.TopicPartition
import org.apache.spark.streaming.kafka010.OffsetRange

import java.sql.{Connection, DriverManager, PreparedStatement, ResultSet}
import scala.collection.mutable

/**
 * @BelongsProject: test33
 * @BelongsPackage: com.hlb.process
 * @CreateTime : 2023/12/28 9:49
 * @Description: 编写Kafka偏移量的公共方法
 * @Author: code_hlb
 */
object OffsetUtils {
  /**
   * 将消费者组的offset信息存入mysql
   *
   * @param groupId 消费者组名称
   * @param offsets 偏移量信息
   *                在本地数据库创建 `t_offset表`
   *                CREATE TABLE `t_offset` (
   *                `topic` varchar(255) NOT NULL,
   *                `partition` int(11) NOT NULL,
   *                `groupid` varchar(255) NOT NULL,
   *                `offset` bigint(20) DEFAULT NULL,
   *                PRIMARY KEY (`topic`,`partition`,`groupid`)
   *                ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
   */
  def getOffsetMap(groupID: String, topic: String): mutable.Map[TopicPartition, Long] = {
    //1.获取连接
    val conn: Connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" +
      "bigdata?characterEncoding=UTF-8&serverTimezone=UTC", "root", "000000")
    //2.编写SQL
    val sql: String = "select `partition`,`offset` from t_offset where groupid = ? and topic = ? "
    //3.获取ps
    val ps: PreparedStatement = conn.prepareStatement(sql)
    //4.设置参数并执行
    ps.setString(1, groupID)
    ps.setString(2, topic)
    val rs: ResultSet = ps.executeQuery()
    //5.获取返回值并封装成map
    val offsetMap: mutable.Map[TopicPartition, Long] = mutable.Map[TopicPartition, Long]()
    while (rs.next()) {
      val partition: Int = rs.getInt("partition")
      val offset: Int = rs.getInt("offset")
      offsetMap += new TopicPartition(topic, partition) -> offset
    }
    //6.关闭资源
    rs.close()
    ps.close()
    conn.close()
    //7.返回map
    offsetMap
  }

  def saveOffsets(groupId: String, offsets: Array[OffsetRange]) = {
    //1.加载驱动并获取连接
    val conn: Connection = DriverManager.getConnection("jdbc:mysql://localhost:3306" +
      "/bigdata?characterEncoding=UTF-8&serverTimezone=UTC", "root", "000000")
    //2.编写SQL//jdbc:mysql://localhost:3306/bigdata
    val sql: String = "replace into t_offset (`topic`,`partition`,`groupid`,`offset`) values(?,?,?,?)"
    //3.创建预编译语句对象
    val ps: PreparedStatement = conn.prepareStatement(sql)
    //4.设置参数执行
    for (o <- offsets) {
      ps.setString(1, o.topic)
      ps.setInt(2, o.partition)
      ps.setString(3, groupId)
      ps.setLong(4, o.untilOffset)
      ps.executeUpdate()
    }
    //5.关闭资源
    ps.close()
    conn.close()
  }
}
