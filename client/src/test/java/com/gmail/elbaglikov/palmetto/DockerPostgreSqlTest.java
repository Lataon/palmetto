package com.gmail.elbaglikov.palmetto;

import com.gmail.elbaglikov.palmetto.config.TestData;
import com.gmail.elbaglikov.palmetto.jpa.OrderRepository;
import com.gmail.elbaglikov.palmetto.model.Order;
import com.gmail.elbaglikov.palmetto.service.OrderService;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DirtiesContext
public class DockerPostgreSqlTest {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderService orderService;

    @Test
    public void getByIdTest() {
        orderRepository.save(TestData.NEW_ORDER);
        TestData.NEW_ORDER.setId(100000L);
        Optional<Order> actual = orderService.getById(100000);
        assertThat(actual).isNotEmpty();
        MatcherAssert.assertThat(actual.get(), Matchers.equalTo(TestData.NEW_ORDER));
    }

}
