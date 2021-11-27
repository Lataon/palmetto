package com.gmail.elbaglikov.palmetto.engine;

import com.gmail.elbaglikov.palmetto.Constants;
import com.gmail.elbaglikov.palmetto.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

    private final KafkaTemplate<String, Order> kafkaTemplate;

    @Autowired
    public KafkaProducer(KafkaTemplate<String, Order> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(Order order) {
        LOGGER.info("sending payload='{}' to topic='{}'", order, Constants.TOPIC_ORDER_NAME);
        kafkaTemplate.send(Constants.TOPIC_ORDER_NAME, order);
    }
}
