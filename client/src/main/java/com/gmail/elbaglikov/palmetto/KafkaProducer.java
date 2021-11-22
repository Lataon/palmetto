package com.gmail.elbaglikov.palmetto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, String msg) {
        LOGGER.info("sending payload='{}' to topic='{}'", msg, topic);
        for (int i =0; i<5; i++)
        kafkaTemplate.send(topic, msg+i);
    }
}