package com.example.order.infrastructure.repository;

import org.springframework.stereotype.Component;

import com.example.order.domain.model.Order;

@Component
public class OrderMapper {

    public Order toDomain(OrderEntity entity) {
        if (entity == null) {
            return null;
        }

        Order order = new Order(
            entity.getId(),
            entity.getOrderNumber(),
            entity.getCustomerId(),
            entity.getOrderDate(),
            entity.getStatus(),
            null  // items will be set separately if needed
        );

        return order;
    }

    public OrderEntity toEntity(Order domain) {
        if (domain == null) {
            return null;
        }

        OrderEntity entity = new OrderEntity(
            domain.getId(),
            domain.getOrderNumber(),
            domain.getCustomerId(),
            domain.getOrderDate(),
            domain.getStatus(),
            null  // items will be set separately if needed
        );

        return entity;
    }
}
