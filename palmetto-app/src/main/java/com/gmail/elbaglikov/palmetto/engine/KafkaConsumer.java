package com.gmail.elbaglikov.palmetto.engine;

import com.gmail.elbaglikov.palmetto.Constants;
import com.gmail.elbaglikov.palmetto.model.Order;
import com.gmail.elbaglikov.palmetto.model.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Component
public class KafkaConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    private static final String getTopicOrdersName = "";

    @Autowired
    private KafkaProducer producer;

    @KafkaListener(topics =  Constants.TOPIC_ORDER_NAME,
            containerFactory = "kafkaListenerContainerFactory")
    public void listenPartition(Order order) {
        LOGGER.info("kafkaListenerContainerFactory received order='{}'", order);
        order.setStatus(OrderStatus.COOK);
        producer.send(Constants.TOPIC_NOTIFICATION_NAME, order);
        CountDownLatch latch = new CountDownLatch(1);
        try {
            latch.await(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            LOGGER.info("interrupt awaiting: " + e.getMessage());
        }
        order.setStatus(OrderStatus.READY);
        producer.send(Constants.TOPIC_NOTIFICATION_NAME, order);
    }
}
