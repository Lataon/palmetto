package com.gmail.elbaglikov.palmetto.config;

import com.gmail.elbaglikov.palmetto.Constants;
import com.gmail.elbaglikov.palmetto.model.Order;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class KafkaTestConsumer {

    private Order order;
    private CountDownLatch latch = new CountDownLatch(1);

    @KafkaListener(topics =  Constants.TOPIC_ORDER_NAME,
            containerFactory = "kafkaListenerContainerFactory")
    public void listenPartition(Order order) {
        setOrder(order);
        latch.countDown();
    }


    public Order getOrder() {
        return order;
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
