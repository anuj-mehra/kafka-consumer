package com.poc.kafkaconsumer

import com.poc.kafkaconsumer.config.{KafkaConnectionContants, KafkaConsumerConfig}
import com.poc.kafkaconsumer.jsonkafkaconsumer.AlwaysReadFromTheStart

import java.util.Properties

object AlwaysReadFromTheStartApp extends App{

  val consumerConfig = KafkaConsumerConfig("/Users/anujmehra/git/kafka-consumer/src/main/resources/application.conf")

  println(consumerConfig.bootstrapServers)

  val properties = new Properties
  properties.put(KafkaConnectionContants.BootstrapServers, consumerConfig.bootstrapServers)

  AlwaysReadFromTheStart.process(properties, consumerConfig)

}
