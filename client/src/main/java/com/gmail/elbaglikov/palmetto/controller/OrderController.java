package com.gmail.elbaglikov.palmetto.controller;

import com.gmail.elbaglikov.palmetto.exception.OrderNotFoundException;
import com.gmail.elbaglikov.palmetto.model.Order;
import com.gmail.elbaglikov.palmetto.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    private final OrderService service;

    @Autowired
    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping("/send-order")
    public Order newOrder (@RequestBody Order order) {
        return service.create(order);
    }

    @GetMapping("/get-order-status/{id}")
    public Order getOrder(@PathVariable Long id) {
        return service.getById(id).orElseThrow(() -> new OrderNotFoundException(id));
    }
}
