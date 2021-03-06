package com.gmail.elbaglikov.palmetto.engine;

import com.gmail.elbaglikov.palmetto.Constants;
import com.gmail.elbaglikov.palmetto.model.Order;
import com.gmail.elbaglikov.palmetto.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    private OrderService orderService;

    @Autowired
    public KafkaConsumer(OrderService orderService) {
        this.orderService = orderService;
    }

    @KafkaListener(topics =  Constants.TOPIC_NOTIFICATION_NAME,
            containerFactory = "kafkaListenerContainerFactory")
    public void listenNotifications(Order order) {
        LOGGER.info("client kafkaListenerContainerFactory received order='{}'", order);
        boolean ifUpdate = orderService.updateStatus(order);
        LOGGER.info("status has updated '{}'successfully", ifUpdate?"":"not ");
    }
}
