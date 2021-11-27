package com.gmail.elbaglikov.palmetto.engine;

import com.gmail.elbaglikov.palmetto.Constants;
import com.gmail.elbaglikov.palmetto.model.Order;
import com.gmail.elbaglikov.palmetto.model.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;


@Component
public class KafkaConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    private static final String getTopicOrdersName = "";

    @Autowired
    private KafkaProducer producer;

    @KafkaListener(topics =  Constants.TOPIC_NOTIFICATION_NAME,
            containerFactory = "kafkaListenerContainerFactory0")
    public void listenPartition0(Order order) {
        LOGGER.info("kafkaListenerContainerFactory0 received order='{}'", order);
        order.setStatus(OrderStatus.DELIVER);
        producer.send(Constants.TOPIC_NOTIFICATION_NAME, order);
    }

    @KafkaListener(topics =   Constants.TOPIC_NOTIFICATION_NAME,
            containerFactory = "kafkaListenerContainerFactory1")
    public void listenPartition1(Order order) {
        LOGGER.info("kafkaListenerContainerFactory1 received order='{}'", order);
        order.setStatus(OrderStatus.DELIVER);
        producer.send(Constants.TOPIC_NOTIFICATION_NAME, order);
    }

    @KafkaListener(topics = Constants.TOPIC_NOTIFICATION_NAME,
            containerFactory = "kafkaListenerContainerFactory2")
    public void listenPartition2(Order order) {
        LOGGER.info("kafkaListenerContainerFactory2 received order='{}'", order);
        order.setStatus(OrderStatus.DELIVER);
        producer.send(Constants.TOPIC_NOTIFICATION_NAME, order);
    }
}
