package com.poc.kafkaconsumer


import com.poc.kafkaconsumer.config.{KafkaConnectionContants, KafkaConsumerConfig}
import com.poc.kafkaconsumer.jsonkafkaconsumer.JsonMessageKafkaConsumer

import java.util.Properties

object JsonMessageKafkaConsumerApp extends App{

  val consumerConfig = KafkaConsumerConfig("/Users/anujmehra/git/kafka-consumer/src/main/resources/application.conf")

  println(consumerConfig.bootstrapServers)

  val properties = new Properties
  properties.put(KafkaConnectionContants.BootstrapServers, consumerConfig.bootstrapServers)

  val consumer = JsonMessageKafkaConsumer.apply
  consumer.process(properties, consumerConfig)
}
