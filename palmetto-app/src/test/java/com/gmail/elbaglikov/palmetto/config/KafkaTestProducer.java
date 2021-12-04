package com.gmail.elbaglikov.palmetto.config;

import com.gmail.elbaglikov.palmetto.Constants;
import com.gmail.elbaglikov.palmetto.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaTestProducer {
    private final KafkaTemplate<String, Order> kafkaTemplate;

    @Autowired
    public KafkaTestProducer(KafkaTemplate<String, Order> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(Order order) {
        kafkaTemplate.send(Constants.TOPIC_ORDER_NAME, order);
    }
}
