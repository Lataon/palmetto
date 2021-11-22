# To start messaging with docker-compose installed go to terminal and run

```sh
$ docker-compose -f ./docker-compose.yml up -d
```
docker exec -it kafka_conf_kafka_1 bash
/bin/kafka-topics --create --topic test --partitions 1 --replication-factor 1 --bootstrap-server kafka:9092
/bin/kafka-console-producer --topic test --bootstrap-server kafka:9092
/bin/kafka-console-consumer --topic test --from-beginning --bootstrap-server kafka:9092
