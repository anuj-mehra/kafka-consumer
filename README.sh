## Start Kafka
cd /users/anujmehra/apps/kafka_2.13-2.7.0/bin/
./zookeeper-server-start.sh   /users/anujmehra/apps/kafka_2.13-2.7.0/config/zookeeper.properties
./kafka-server-start.sh  /users/anujmehra/apps/kafka_2.13-2.7.0/config/server.properties
./kafka-console-producer.sh -broker-list localhost:9092 -topic topic-1

##Important topics;
1. How to commit offsets
2. How to use the checkpoint location
3. How to stop the kafka consumer
4.


