package com.gmail.elbaglikov.palmetto.config;

import com.gmail.elbaglikov.palmetto.Constants;
import com.gmail.elbaglikov.palmetto.model.Order;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Component
public class KafkaTestConsumer {

    private List<Order> orders = new ArrayList<>();
    private CountDownLatch latch = new CountDownLatch(2);

    @KafkaListener(topics =  Constants.TOPIC_NOTIFICATION_NAME,
            containerFactory = "kafkaTestListenerContainerFactory")
    public void listenPartition(Order order) {
        orders.add(order);
        latch.countDown();
    }


    public List<Order> getOrders() {
        return orders;
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
