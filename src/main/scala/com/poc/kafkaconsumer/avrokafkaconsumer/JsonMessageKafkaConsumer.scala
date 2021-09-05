package com.poc.kafkaconsumer.avrokafkaconsumer

import com.poc.kafkaconsumer.JsonMessageKafkaConsumerApp.{consumerConfig}
import com.poc.kafkaconsumer.config.{KafkaConnectionContants, KafkaConsumerConfig}
import org.apache.kafka.clients.consumer.{ConsumerRecords, KafkaConsumer}

import java.time.Duration
import java.util.Properties

class JsonMessageKafkaConsumer(properties: Properties, config: KafkaConsumerConfig) {

  def process: Unit = {
    properties.put(KafkaConnectionContants.GroupId, consumerConfig.jsonMsgConsumerGroupId)
    properties.put(KafkaConnectionContants.KeyDeserializer, config.jsonMsgKeySerializer)
    properties.put(KafkaConnectionContants.ValueDeserializer, config.jsonMsgValueSerializer)


    // creating and subscribing the consumer
    val consumer = new KafkaConsumer[String, String](properties)
    consumer.subscribe(java.util.Collections.singletonList(config.jsonMsgTopicName))

    // polling the topic
    try{
      while(true){
        println("----------------------------------")
        val consumerRecords: ConsumerRecords[String, String] = consumer.poll(Duration.ofSeconds(20))

        consumerRecords.forEach(record => {
          println("topic ==>" + record.topic())
          println("offset ==>" + record.offset())
          println("partition ==>" + record.partition())
          println("key ==>" + record.key())
          println("value ==>" + record.value())

        })

        // commit sync
        consumer.commitSync()
      }
    }finally {
      consumer.close
      println("DONE")
    }

  }

}

object JsonMessageKafkaConsumer{

  def apply(properties: Properties, config: KafkaConsumerConfig) = new JsonMessageKafkaConsumer(properties, config)
}
