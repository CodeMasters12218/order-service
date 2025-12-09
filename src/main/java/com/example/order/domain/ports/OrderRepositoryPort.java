package com.example.order.domain.ports;

import java.util.List;
import java.util.Optional;

import com.example.order.domain.model.Order;

public interface OrderRepositoryPort {
    List<Order> findAll();
    Optional<Order> findById(Integer id);
    Order save(Order order);
    void deleteById(Integer id);
}
