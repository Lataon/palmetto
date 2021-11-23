package com.gmail.elbaglikov.palmetto.engine;

import com.gmail.elbaglikov.palmetto.Constants;
import com.gmail.elbaglikov.palmetto.model.Order;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    private static final String getTopicOrdersName = "";


    @KafkaListener(topics = Constants.TOPIC_ORDER_NAME, containerFactory = "kafkaListenerContainerFactory")
    public void listenGroup(Order order) {
        LOGGER.info("received order='{}'", order);
    }
}
