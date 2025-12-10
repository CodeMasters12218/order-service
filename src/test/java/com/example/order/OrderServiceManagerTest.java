package com.example.order;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.order.application.OrderServiceManager;
import com.example.order.domain.model.Order;
import com.example.order.domain.model.OrderItem;
import com.example.order.domain.ports.OrderRepositoryPort;
import com.example.order.infrastructure.repository.OrderJpaRepository;

@ExtendWith(MockitoExtension.class)
class OrderServiceManagerTest {

    @Mock
    private OrderRepositoryPort repository;

    @Mock
    private OrderJpaRepository jpaRepository;

    @InjectMocks
    private OrderServiceManager service;

    @Test
    void testUpdateOrder() {
        // Arrange
        Order existingOrder = new Order();
        existingOrder.setId(1);
        existingOrder.setOrderNumber("ORD-001");
        existingOrder.setCustomerId(100);

        Order newOrderData = new Order();
        newOrderData.setOrderNumber("ORD-002");
        newOrderData.setCustomerId(200);
        newOrderData.setItems(Arrays.asList(new OrderItem()));

        when(repository.findById(1)).thenReturn(Optional.of(existingOrder));
        when(repository.save(any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Order updatedOrder = service.update(1, newOrderData);

        // Assert
        assertEquals("ORD-002", updatedOrder.getOrderNumber());
        assertEquals(200, updatedOrder.getCustomerId());
        assertNotNull(updatedOrder.getItems());
        verify(repository).findById(1);
        verify(repository).save(existingOrder);
    }
}
