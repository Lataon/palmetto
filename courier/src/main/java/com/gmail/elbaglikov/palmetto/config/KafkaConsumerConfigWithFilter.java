package com.gmail.elbaglikov.palmetto.config;

import com.gmail.elbaglikov.palmetto.model.Order;
import com.gmail.elbaglikov.palmetto.model.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;

@EnableKafka
@Configuration
public class KafkaConsumerConfigWithFilter {

    @Autowired
    private ConsumerFactory<String, Order> consumerFactory;

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Order> kafkaListenerContainerFactoryWithFilter() {
        ConcurrentKafkaListenerContainerFactory<String, Order> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.setRecordFilterStrategy(
                record -> record.value().getStatus() != OrderStatus.READY);
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.BATCH);
        factory.setConcurrency(3);
        return factory;
    }
}
