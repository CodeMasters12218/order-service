package com.example.order.domain.ports;

import java.util.List;

import com.example.order.domain.model.Order;
import com.example.order.domain.model.OrderItem;

public interface OrderServicePort {
    List<Order> findAll();
    Order findById(Integer id);
    Order save(Order order);
    Order update(Integer id, Order newOrderData);
    void deleteById(Integer id);
    Order addItem(Integer id, OrderItem item);
    List<OrderItem> getItems(Integer id);
}
