package com.atguigu.util

import kafka.serializer.StringDecoder
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.kafka.KafkaUtils
import org.glassfish.hk2.api.messaging.Topic

object MyKafkaUtil {

  def getKafkaStream(ssc: StreamingContext, topic: String) = {
    val params: Map[String, String] = Map(
      ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG -> PropertiesUtil.getProperty("kafka.broker.list"),
      ConsumerConfig.GROUP_ID_CONFIG -> PropertiesUtil.getProperty("kafka.group")
    )

    KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](ssc, params, Set(topic))
  }
}
