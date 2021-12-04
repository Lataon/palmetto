package com.gmail.elbaglikov.palmetto.service;

import com.gmail.elbaglikov.palmetto.Constants;
import com.gmail.elbaglikov.palmetto.engine.KafkaProducer;
import com.gmail.elbaglikov.palmetto.exception.OrderNotFoundException;
import com.gmail.elbaglikov.palmetto.jpa.OrderRepository;
import com.gmail.elbaglikov.palmetto.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final KafkaProducer kafkaProducer;

    @Autowired
    public OrderService(OrderRepository orderRepository, KafkaProducer kafkaProducer) {
        this.orderRepository = orderRepository;
        this.kafkaProducer = kafkaProducer;
    }

    public Order create(Order order) {
        Order currentOrder = orderRepository.save(order);
        kafkaProducer.send(Constants.TOPIC_ORDER_NAME, currentOrder);
        return currentOrder;
    }

    public Optional<Order> getById (long id) {
        return orderRepository.findById(id);
    }

    public boolean updateStatus(Order order) {
        if (order.isNew()) throw new OrderNotFoundException(order.getId());
        return orderRepository.setStatus(order.getId(), order.getStatus())!=0;
    }
}
