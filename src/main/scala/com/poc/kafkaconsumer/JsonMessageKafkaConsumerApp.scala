package com.poc.kafkaconsumer

import com.poc.kafkaconsumer.avrokafkaconsumer.JsonMessageKafkaConsumer
import com.poc.kafkaconsumer.config.{KafkaConnectionContants, KafkaConsumerConfig}

import java.util.Properties

object JsonMessageKafkaConsumerApp extends App{

  val consumerConfig = KafkaConsumerConfig("/Users/anujmehra/git/kafka-consumer/src/main/resources/application.conf")

  println(consumerConfig.bootstrapServers)

  val properties = new Properties
  properties.put(KafkaConnectionContants.BootstrapServers, consumerConfig.bootstrapServers)

  val consumer = JsonMessageKafkaConsumer(properties, consumerConfig)
  consumer.process
}
