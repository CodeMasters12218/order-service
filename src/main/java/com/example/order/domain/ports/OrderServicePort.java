package com.example.order.domain.ports;

import java.time.LocalDateTime;
import java.util.List;

import com.example.order.domain.model.Order;
import com.example.order.domain.model.OrderItem;
import com.example.order.domain.model.OrderStatus;
import com.example.order.infrastructure.repository.OrderEntity;

public interface OrderServicePort {
    List<Order> findAll();
    Order findById(Integer id);
    Order save(Order order);
    Order update(Integer id, Order newOrderData);
    void deleteById(Integer id);
    Order addItem(Integer id, OrderItem item);
    List<OrderItem> getItems(Integer id);
    List<OrderEntity> findByStatus(OrderStatus status);
    List<OrderEntity> findByCustomerId(Integer customerId);
    List<OrderEntity> buscarPedidosRecientes(LocalDateTime date);
    OrderEntity buscarPorNumero(String orderNumber);
}
