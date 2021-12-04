package com.gmail.elbaglikov.palmetto;

import org.junit.jupiter.api.Test;
import org.springframework.kafka.test.context.EmbeddedKafka;

@EmbeddedKafka(partitions = 3, brokerProperties = { "listeners=PLAINTEXT://localhost:29092", "port=29092" }, topics = {Constants.TOPIC_ORDER_NAME, Constants.TOPIC_NOTIFICATION_NAME})
class EmbeddedKafkaTest extends AbstractClientTest {

    @Test
    @Override
    public void testProducer() throws Exception {
        super.testProducer();
    }

    @Test
    @Override
    public void testConsumerAndJpa() throws InterruptedException {
        super.testConsumerAndJpa();
    }
}
