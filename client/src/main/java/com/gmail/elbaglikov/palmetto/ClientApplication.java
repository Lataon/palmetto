package com.gmail.elbaglikov.palmetto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class ClientApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ClientApplication.class, args);
        KafkaProducer kafkaProducer = context.getBean(KafkaProducer.class);
        KafkaConsumer kafkaConsumer = context.getBean(KafkaConsumer.class);
        kafkaProducer.send("test", "hello world");
        CountDownLatch latch = new CountDownLatch(3);
        try {
            latch.await(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
