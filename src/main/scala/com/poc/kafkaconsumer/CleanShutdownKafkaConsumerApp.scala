package com.poc.kafkaconsumer

// to trigger a shutdown, our consumer must subscribe to two topics.
/*
1. where data is being fetched
2. Shutdown topic
Number of partitions in shutdown topic is same as the number of partitions in the data-topic.
Consumer subscribes to both the topics...
This approach help in all the following scenarios;
1. single consumer
2. multiple consumers on cloud
3. multiple consumers on-prim

On receiving the stop message, the application is shutdown.

We need a separate producerApp, that sends STOP message to the kafka consumer topic
 */
object CleanShutdownKafkaConsumerApp extends App{


}

class CleanShutdownKafkaConsumerApp extends Serializable {

  def consumer(): Unit ={

  }
}

/*
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import java.util.Collections;
import java.util.Properties;

public class ConsumerWithStop {
    private volatile boolean running = true;
    private KafkaConsumer<String, String> consumer;

    public ConsumerWithStop(Properties props) {
        consumer = new KafkaConsumer<>(props);
    }

    public void startConsuming() {
        consumer.subscribe(Collections.singletonList("main-topic"));
        new Thread(this::listenForStopMessage).start();

        while (running) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                // Process main-topic messages
            }
        }
        consumer.close();
    }

    private void listenForStopMessage() {
        KafkaConsumer<String, String> stopConsumer = new KafkaConsumer<>(consumer.props());
        stopConsumer.subscribe(Collections.singletonList("stop-topic"));

        while (running) {
            ConsumerRecords<String, String> stopRecords = stopConsumer.poll(100);
            for (ConsumerRecord<String, String> record : stopRecords) {
                if ("STOP".equals(record.value())) {
                    running = false;
                    break;
                }
            }
        }
        stopConsumer.close();
    }

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "test-group");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        ConsumerWithStop consumer = new ConsumerWithStop(props);
        consumer.startConsuming();
    }
}

 */
