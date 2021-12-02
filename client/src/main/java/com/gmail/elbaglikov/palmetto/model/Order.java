package com.gmail.elbaglikov.palmetto.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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

    @Column(name = "clientName", nullable = false)
    @NotNull
    private String clientName;

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
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDateTime time;

    public Order() {
    }

    public boolean isNew() {
        return id == null;
    }

    public Order(Long id, String clientEmail, String clientName, String orderName, String deliveryAddress, OrderStatus status, LocalDateTime time) {
        this.id = id;
        this.clientEmail = clientEmail;
        this.clientName = clientName;
        this.orderName = orderName;
        this.deliveryAddress = deliveryAddress;
        this.status = status;
        this.time = time;
    }

    public Order(String clientEmail, String clientName, String orderName, String deliveryAddress, OrderStatus status, LocalDateTime time) {
        this(null, clientEmail, clientName, orderName, deliveryAddress, status, time);
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

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clintName) {
        this.clientName = clintName;
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

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                clientEmail.equals(order.clientEmail) &&
                clientName.equals(order.clientName) &&
                orderName.equals(order.orderName) &&
                deliveryAddress.equals(order.deliveryAddress) &&
                status == order.status &&
                time.equals(order.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clientEmail, clientName, orderName, deliveryAddress, status, time);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", clientEmail='" + clientEmail + '\'' +
                ", clintName='" + clientName + '\'' +
                ", orderName='" + orderName + '\'' +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", status=" + status +
                ", time=" + time +
                '}';
    }
}
