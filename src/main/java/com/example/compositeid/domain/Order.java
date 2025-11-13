package com.example.compositeid.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table("ORDERS")
public class Order {

    @Id
    @Embedded.Nullable
    private OrderId id;

    private LocalDate orderDate;

    private String description;

    public Order(OrderId id, LocalDate orderDate, String description) {
        this.id = id;
        this.orderDate = orderDate;
        this.description = description;
    }

    public OrderId getId() {
        return id;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderDate=" + orderDate +
                ", description='" + description + '\'' +
                '}';
    }
}
