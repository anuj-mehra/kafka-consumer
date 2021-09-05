package com.poc.kafkaconsumer.config

import com.typesafe.config.ConfigFactory

import java.io.File

class KafkaConsumerConfig(applicationConfFile: String) extends Serializable {

  val config = ConfigFactory.parseFile(new File(applicationConfFile)).resolve

  /*schema.registry.url = ""
  ssl.truststore.location=""
  ssl.truststore.password=""
  service.discovery.url = ""*/
  /* security.protocol= ""
   sasl.mechanism = ""
   kafka.username=""
   kafka.password=""*/

  lazy val bootstrapServers= config.getString("consumer_config.bootstrap_servers")
  lazy val jsonMsgConsumerGroupId = config.getString("consumer_config.json_message.consumer_group_id")
  lazy val jsonMsgTopicName = config.getString("consumer_config.json_message.topic_name")
  lazy val jsonMsgKeySerializer = config.getString("consumer_config.json_message.key_serializer")
  lazy val jsonMsgValueSerializer = config.getString("consumer_config.json_message.value_serializer")

  lazy val avroMsgConsumerGroupId = config.getString("consumer_config.avro_message.consumer_group_id")
  lazy val avroMsgTopicName = config.getString("consumer_config.avro_message.topic_name")
  lazy val avroMsgKeySerializer = config.getString("consumer_config.avro_message.key_serializer")
  lazy val avroMsgValueSerializer = config.getString("consumer_config.avro_message.value_serializer")

}

object KafkaConsumerConfig {
   def apply(applicationConfFile: String) = new KafkaConsumerConfig(applicationConfFile)
}
