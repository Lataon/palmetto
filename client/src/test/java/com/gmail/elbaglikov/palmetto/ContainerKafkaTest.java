package com.gmail.elbaglikov.palmetto;

import com.gmail.elbaglikov.palmetto.model.Order;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.HashMap;
import java.util.Map;

@Testcontainers
@Import(ContainerKafkaTest.KafkaTestContainersConfiguration.class)
public class ContainerKafkaTest extends AbstractClientTest{
    @Container
    private static KafkaContainer kafka =
            new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:latest"));

    @TestConfiguration
    static class KafkaTestContainersConfiguration {
        @Bean
        public ConsumerFactory<String, Order> consumerFactory() {
            Map<String, Object> props = new HashMap<>();
            props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafka.getBootstrapServers());
            props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
            props.put(ConsumerConfig.GROUP_ID_CONFIG, "test-client");
            return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(Order.class));
        }

        @Bean
        public ProducerFactory<String, Order> producerFactory() {
            Map<String, Object> configProps = new HashMap<>();
            configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafka.getBootstrapServers());
            configProps.put(
                    ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                    StringSerializer.class);
            configProps.put(
                    ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                    JsonSerializer.class);
            return new DefaultKafkaProducerFactory<>(configProps);
        }

        @Bean
        public ConcurrentKafkaListenerContainerFactory<String, Order> kafkaListenerContainerFactory() {
            ConcurrentKafkaListenerContainerFactory<String, Order> factory =
                    new ConcurrentKafkaListenerContainerFactory<>();
            factory.setConsumerFactory(consumerFactory());
            factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.BATCH);
            factory.setConcurrency(3);
            return factory;
        }

        @Bean
        public KafkaTemplate<String, Order> kafkaTemplate() {
            return new KafkaTemplate<>(producerFactory());
        }
    }
}
