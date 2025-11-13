package com.example.compositeid.domain;

import org.springframework.data.relational.core.mapping.Column;

import java.util.Objects;

public class OrderId {

    @Column("customer_id")
    private String customerId;

    @Column("order_number")
    private Long orderNumber;

    public OrderId(String customerId, Long orderNumber) {
        this.customerId = customerId;
        this.orderNumber = orderNumber;
    }

    public String getCustomerId() {
        return customerId;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderId that = (OrderId) o;

        if (!Objects.equals(customerId, that.customerId)) return false;
        return Objects.equals(orderNumber, that.orderNumber);
    }

    @Override
    public int hashCode() {
        int result = customerId != null ? customerId.hashCode() : 0;
        result = 31 * result + (orderNumber != null ? orderNumber.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OrderId{" +
                "customerId='" + customerId + '\'' +
                ", orderNumber=" + orderNumber +
                '}';
    }
}
