package com.poc.kafkaconsumer.config

object KafkaConnectionContants extends Enumeration {

  type Main = Value

  // Assigning values
  val BootstrapServers = Value("bootstrap.servers").toString
  val GroupId = Value("group.id").toString
  val KeyDeserializer = Value("key.deserializer").toString
  val ValueDeserializer = Value("value.deserializer").toString
}
