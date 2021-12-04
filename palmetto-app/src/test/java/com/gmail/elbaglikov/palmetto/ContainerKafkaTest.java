package com.gmail.elbaglikov.palmetto;

import com.gmail.elbaglikov.palmetto.config.KafkaTestConsumer;
import com.gmail.elbaglikov.palmetto.config.KafkaTestProducer;
import com.gmail.elbaglikov.palmetto.config.TestData;
import com.gmail.elbaglikov.palmetto.engine.KafkaProducer;
import com.gmail.elbaglikov.palmetto.model.Order;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.test.annotation.DirtiesContext;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@DirtiesContext
@SpringBootTest(properties = "spring.main.allow-bean-definition-overriding=true")
@Testcontainers
@Import(ContainerKafkaTest.KafkaTestContainersConfiguration.class)
public class ContainerKafkaTest {
    @Container
    private static KafkaContainer kafka =
            new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:latest"));

    @TestConfiguration
    static class KafkaTestContainersConfiguration {
        @Bean
        public ConsumerFactory<String, Order> consumerTestFactory() {
            Map<String, Object> props = new HashMap<>();
            props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafka.getBootstrapServers());
            props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
            props.put(ConsumerConfig.GROUP_ID_CONFIG, "test-palmetto");
            return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(Order.class));
        }

        @Bean
        public ConsumerFactory<String, Order> consumerFactory() {
            Map<String, Object> props = new HashMap<>();
            props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafka.getBootstrapServers());
            props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
            props.put(ConsumerConfig.GROUP_ID_CONFIG, "palmetto");
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
        public ConcurrentKafkaListenerContainerFactory<String, Order> kafkaTestListenerContainerFactory() {
            ConcurrentKafkaListenerContainerFactory<String, Order> factory =
                    new ConcurrentKafkaListenerContainerFactory<>();
            factory.setConsumerFactory(consumerTestFactory());
            factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.BATCH);
            factory.setConcurrency(3);
            return factory;
        }

        @Bean
        public KafkaTemplate<String, Order> kafkaTemplate() {
            return new KafkaTemplate<>(producerFactory());
        }
    }

    @Autowired
    private KafkaTestConsumer testConsumer;

    @Autowired
    private KafkaProducer producer;

    @Autowired
    private KafkaTestProducer testProducer;

    @Test
    public void testProducer() throws InterruptedException {
        producer.send(Constants.TOPIC_NOTIFICATION_NAME, TestData.ORDER_READY);
        testConsumer.getLatch().await(10000, TimeUnit.MILLISECONDS);
        assertThat(testConsumer.getLatch().getCount(), equalTo(1L));
        assertThat(testConsumer.getOrders(), hasItem(TestData.ORDER_READY));
    }

    @Test
    public void TestConsumer() throws InterruptedException {
        testProducer.send(TestData.ORDER_WAIT);
        testConsumer.getLatch().await(25000, TimeUnit.MILLISECONDS);// wait for 25 sec
        assertThat(testConsumer.getLatch().getCount(), equalTo(0L));
        assertThat(testConsumer.getOrders(), hasItem(TestData.ORDER_COOK));
        assertThat(testConsumer.getOrders(), hasItem(TestData.ORDER_READY));
    }
}
