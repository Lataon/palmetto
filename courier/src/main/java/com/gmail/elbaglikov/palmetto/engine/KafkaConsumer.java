package com.gmail.elbaglikov.palmetto.engine;

import com.gmail.elbaglikov.palmetto.Constants;
import com.gmail.elbaglikov.palmetto.model.Order;
import com.gmail.elbaglikov.palmetto.model.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
public class KafkaConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    @Autowired
    private KafkaProducer producer;

    @KafkaListener(topics =  Constants.TOPIC_NOTIFICATION_NAME,
            containerFactory = "kafkaListenerContainerFactoryWithFilter")
    public void listenPartition0(Order order) {
        LOGGER.info("courier kafkaListenerContainerFactoryWithFilter received order='{}'", order);
        order.setStatus(OrderStatus.DELIVER);
        producer.send(Constants.TOPIC_NOTIFICATION_NAME, order);
    }
}
