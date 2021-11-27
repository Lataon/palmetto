package com.gmail.elbaglikov.palmetto.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "clientEmail", nullable = false)
    @NotNull
    private String clientEmail;

    @Column(name = "clintName", nullable = false)
    @NotNull
    private String clintName;

    @Column(name = "orderName", nullable = false)
    @NotNull
    private String orderName;

    @Column(name = "deliveryAddress", nullable = false)
    @NotNull
    private String deliveryAddress;

    @Column(name = "status", nullable = false)
    @NotNull
    private OrderStatus status;

    @Column(name = "timeOrder", nullable = false)
    @NotNull
    private LocalDateTime timeOrder;

    public Order() {
    }

    public boolean isNew() {
        return id == null;
    }

    public Order(Long id, String clientEmail, String clintName, String orderName, String deliveryAddress, OrderStatus status, LocalDateTime timeOrder) {
        this.id = id;
        this.clientEmail = clientEmail;
        this.clintName = clintName;
        this.orderName = orderName;
        this.deliveryAddress = deliveryAddress;
        this.status = status;
        this.timeOrder = timeOrder;
    }

    public Order(String clientEmail, String clintName, String orderName, String deliveryAddress, OrderStatus status, LocalDateTime timeOrder) {
        this(null, clientEmail, clintName, orderName, deliveryAddress, status, timeOrder);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClintName() {
        return clintName;
    }

    public void setClintName(String clintName) {
        this.clintName = clintName;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public LocalDateTime getTimeOrder() {
        return timeOrder;
    }

    public void setTimeOrder(LocalDateTime timeOrder) {
        this.timeOrder = timeOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                clientEmail.equals(order.clientEmail) &&
                clintName.equals(order.clintName) &&
                orderName.equals(order.orderName) &&
                deliveryAddress.equals(order.deliveryAddress) &&
                status == order.status &&
                timeOrder.equals(order.timeOrder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clientEmail, clintName, orderName, deliveryAddress, status, timeOrder);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", clientEmail='" + clientEmail + '\'' +
                ", clintName='" + clintName + '\'' +
                ", orderName='" + orderName + '\'' +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", status=" + status +
                ", timeOrder=" + timeOrder +
                '}';
    }
}
