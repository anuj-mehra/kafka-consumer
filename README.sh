## Start Kafka
cd /users/anujmehra/apps/kafka_2.13-2.7.0/bin/
./zookeeper-server-start.sh   /users/anujmehra/apps/kafka_2.13-2.7.0/config/zookeeper.properties
./kafka-server-start.sh  /users/anujmehra/apps/kafka_2.13-2.7.0/config/server.properties
./kafka-console-producer.sh --broker-list localhost:9092 --topic topic-1

## Better to use Confluent Kafka
Install confluent Kafka;
https://www.youtube.com/watch?v=5x5GnBhyTMI

Start confluent cluster;
confluent local services start

## Start Confluent Kafka consumer on shell
kafka-console-consumer --topic topic-1 --bootstrap-server localhost:9092 --from-beginning

##Important topics;
1. How to commit offsets
2. How to use the checkpoint location
3. How to stop the kafka consumer
4.


