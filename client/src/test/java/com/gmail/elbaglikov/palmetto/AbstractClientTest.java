package com.gmail.elbaglikov.palmetto;

import com.gmail.elbaglikov.palmetto.config.KafkaTestConsumer;
import com.gmail.elbaglikov.palmetto.config.KafkaTestProducer;
import com.gmail.elbaglikov.palmetto.config.TestData;
import com.gmail.elbaglikov.palmetto.engine.KafkaProducer;
import com.gmail.elbaglikov.palmetto.model.Order;
import com.gmail.elbaglikov.palmetto.model.OrderStatus;
import com.gmail.elbaglikov.palmetto.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@DirtiesContext
@SpringBootTest(properties = "spring.main.allow-bean-definition-overriding=true")
//@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
public class AbstractClientTest {
    @Autowired
    private KafkaTestConsumer testConsumer;

    @Autowired
    private KafkaProducer producer;

    @Autowired
    private KafkaTestProducer testProducer;

    @Autowired
    private OrderService service;

    @Test
    public void testProducer() throws Exception {
        producer.send(TestData.ORDER);
        testConsumer.getLatch().await(10000, TimeUnit.MILLISECONDS);
        assertThat(testConsumer.getLatch().getCount(), equalTo(0L));
        assertThat(testConsumer.getOrder(), equalTo(TestData.ORDER));
    }

    @Test
    public void testConsumerAndJpa() throws InterruptedException {
        Order currentOrder = service.create(TestData.NEW_ORDER); //put new order to database
        currentOrder.setStatus(OrderStatus.DELIVER); //change status
        testProducer.send(currentOrder); //send changed order to Notification topic
        CountDownLatch latch = new CountDownLatch(1);
        latch.await(10000, TimeUnit.MILLISECONDS);// wait for 10 sec
        assertThat(service.getById(currentOrder.getId()).get(), equalTo(TestData.ORDER));
    }
}
