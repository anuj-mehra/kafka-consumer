package com.poc.kafkaconsumer.jsonkafkaconsumer

import com.poc.kafkaconsumer.config.{KafkaConnectionContants, KafkaConsumerConfig}
import org.apache.kafka.clients.consumer.{ConsumerRecords, KafkaConsumer}
import org.apache.kafka.common.TopicPartition

import java.io.File
import java.time.Duration
import java.util
import java.util.Properties
import scala.util.control.Breaks.{break, breakable}

object CommitSpecificOffset {

  def process(properties: Properties, config: KafkaConsumerConfig): Unit = {
    properties.put(KafkaConnectionContants.GroupId, config.jsonMsgConsumerGroupId)
    properties.put(KafkaConnectionContants.KeyDeserializer, config.jsonMsgKeySerializer)
    properties.put(KafkaConnectionContants.ValueDeserializer, config.jsonMsgValueSerializer)

    // creating and subscribing the consumer
    val consumer = new KafkaConsumer[String, String](properties)
    consumer.subscribe(java.util.Collections.singletonList(config.jsonMsgTopicName))

    val existingStopConsumerFile = new File(config.kafkaConsumerStopFile)
    if (existingStopConsumerFile.exists()) {
      existingStopConsumerFile.delete()
    }

    // polling the topic
    breakable {
    while (true) {
        try {
          println("------------ going to read data from kafka ----------------------")
          val consumerRecords: ConsumerRecords[String, String] = consumer.poll(Duration.ofSeconds(10))

          consumerRecords.forEach(record => {
            println("topic ==>" + record.topic())
            println("offset ==>" + record.offset())
            println("partition ==>" + record.partition())
            println("key ==>" + record.key())
            println("value ==>" + record.value())
            import org.apache.kafka.clients.consumer.OffsetAndMetadata

            val offsetAndMetadataMap = new util.HashMap[TopicPartition, OffsetAndMetadata]()
            offsetAndMetadataMap.put(new TopicPartition(record.topic(), record.partition()),
              new OffsetAndMetadata(record.offset()))

            consumer.commitAsync(offsetAndMetadataMap)
            //consumer.commitSync() ==> read only once..
            //consumer.com
          })

        } finally {
          val file = new File(config.kafkaConsumerStopFile)
          if (file.exists()) {
            // commit sync
            consumer.commitSync()
            consumer.close
            println("----- consumer shutdown successfully -----")
            break
          }
          println("DONE")
        }
      }

    }// end of while loop
  }

}
