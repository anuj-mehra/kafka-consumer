package com.poc.kafkaconsumer;


import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

// to trigger a shutdown, our consumer must subscribe to two topics.
/*
1. where data is being fetched
2. a studown topic
 */
public class CleanShutdownKafkaConsumer {
    private final static String TOPIC = "your-topic";
    private final static String BOOTSTRAP_SERVERS = "localhost:9092";
    private static Consumer<String, String> consumer;
    private static final AtomicBoolean closed = new AtomicBoolean(false);

    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(CleanShutdownKafkaConsumer::shutdown));
        consumer = createConsumer();
        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                records.forEach(record -> {
                    System.out.printf("Consumed record with key %s and value %s%n", record.key(), record.value());
                });
            }
        } catch (WakeupException e) {
            // Ignore exception if closing
            if (!closed.get()) {
                throw e;
            }
        } finally {
            consumer.close();
            closed.set(true);
        }
    }

    private static Consumer<String, String> createConsumer() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "KafkaExampleConsumer");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");

        final Consumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList(TOPIC));
        return consumer;
    }

    private static void shutdown() {
        closed.set(true);
        consumer.wakeup();
    }
}
