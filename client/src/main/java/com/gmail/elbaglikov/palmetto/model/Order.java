package com.gmail.elbaglikov.palmetto.model;

import java.util.Objects;

public class Order {
    private Long id;
    private String clientEmail;
    private String clintName;
    private String orderName;
    private String deliveryAddress;
    private OrderStatus status;

    public Order() {
    }

    public Order(Long id, String clientEmail, String clintName, String orderName, String deliveryAddress, OrderStatus status) {
        this.id = id;
        this.clientEmail = clientEmail;
        this.clintName = clintName;
        this.orderName = orderName;
        this.deliveryAddress = deliveryAddress;
        this.status = status;
    }

    public Order(String clientEmail, String clintName, String orderName, String deliveryAddress, OrderStatus status) {
        this(null, clientEmail, clintName, orderName, deliveryAddress, status);
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
                status == order.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clientEmail, clintName, orderName, deliveryAddress, status);
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
                '}';
    }
}
