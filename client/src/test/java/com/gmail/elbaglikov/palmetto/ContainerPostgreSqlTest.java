package com.gmail.elbaglikov.palmetto;

import com.gmail.elbaglikov.palmetto.config.TestData;
import com.gmail.elbaglikov.palmetto.jpa.OrderRepository;
import com.gmail.elbaglikov.palmetto.model.Order;
import com.gmail.elbaglikov.palmetto.model.OrderStatus;
import com.gmail.elbaglikov.palmetto.service.OrderService;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.sql.DataSource;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;

@DirtiesContext
@SpringBootTest
@Testcontainers
@Import(ContainerPostgreSqlTest.TestConfig.class)
public class ContainerPostgreSqlTest {

    @TestConfiguration
    static class TestConfig {
        @Bean(initMethod = "start", destroyMethod = "stop")
        public JdbcDatabaseContainer<?> jdbcDatabaseContainer() {
            return new PostgreSQLContainer<>("postgres:latest")
                    .withInitScript("initDB.sql")
                    .waitingFor(Wait.forListeningPort());// ожидание доступности порта (2)
        }

        @Bean
        public DataSource dataSource(JdbcDatabaseContainer<?> jdbcDatabaseContainer) {
            HikariConfig hikariConfig = new HikariConfig();
            hikariConfig.setJdbcUrl(jdbcDatabaseContainer.getJdbcUrl());
            hikariConfig.setUsername(jdbcDatabaseContainer.getUsername());
            hikariConfig.setPassword(jdbcDatabaseContainer.getPassword());

            return new HikariDataSource(hikariConfig);
        }
    }

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @Test
    public void getByIdTest(){
        orderRepository.save(TestData.NEW_ORDER);
        TestData.NEW_ORDER.setId(100000L);
        Optional<Order> actual = orderService.getById(100000);
        assertThat(actual).isNotEmpty();
        assertThat(actual.get(), Matchers.equalTo(TestData.NEW_ORDER));
    }
}
