package com.example.compositeid.repository;

import com.example.compositeid.domain.Order;

public interface OrderRepository {

    Order save(Order order);

    Iterable<Order> findAll();
}
