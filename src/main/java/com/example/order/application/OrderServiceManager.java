package com.example.order.application;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.common.dto.ProductDTO;
import com.example.order.domain.model.Order;
import com.example.order.domain.model.OrderItem;
import com.example.order.domain.model.OrderStatus;
import com.example.order.domain.ports.OrderRepositoryPort;
import com.example.order.domain.ports.OrderServicePort;
import com.example.order.infrastructure.clients.ProductsClient;
import com.example.order.infrastructure.repository.OrderEntity;
import com.example.order.infrastructure.repository.OrderJpaRepository;

@Service
public class OrderServiceManager implements OrderServicePort {

    private final OrderRepositoryPort repository;
    private final OrderJpaRepository jpaRepository;
    private final ProductsClient productsClient;

    public OrderServiceManager(OrderRepositoryPort repository, OrderJpaRepository jpaRepository, ProductsClient productsClient) {
        this.repository = repository;
        this.jpaRepository = jpaRepository;
        this.productsClient = productsClient;
    }
    @Override
    public List<Order> findAll() {
        return repository.findAll();
    }

    @Override
    public Order findById(Integer id) {
        return this.repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Orden no encontrada con id: " + id));
    }

    @Override
    public Order save(Order order) {
        ProductDTO product = productsClient.obtenerProductoPorId(order.getId());
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PROCESADO);
        
        return repository.save(order);
    }

    @Override
    public Order update(Integer id, Order newOrderData) {
        Order existingOrder = this.repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Orden no encontrada con id: " + id));

        existingOrder.setOrderNumber(newOrderData.getOrderNumber());
        existingOrder.setCustomerId(newOrderData.getCustomerId());
        existingOrder.setOrderDate(newOrderData.getOrderDate());
        
        // Update status if provided
        if (newOrderData.getStatus() != null) {
            existingOrder.setStatus(newOrderData.getStatus());
        }
        
        // Update items if provided
        if (newOrderData.getItems() != null) {
            existingOrder.setItems(newOrderData.getItems());
        }

        return repository.save(existingOrder);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Order addItem(Integer id, OrderItem item) {
        Order order = this.repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Orden no encontrada con id: " + id));
        if (order.getItems() == null) {
            order.setItems(new java.util.ArrayList<>());
        }
        order.getItems().add(item);
        return repository.save(order);
    }

    @Override
    public List<OrderItem> getItems(Integer id) {
        Order order = this.repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Orden no encontrada con id: " + id));
        return order.getItems();
    }

    @Override
    public List<OrderEntity> findByStatus(OrderStatus status) {
        return jpaRepository.findByStatus(status);
    }

    @Override
    public List<OrderEntity> findByCustomerId(Integer customerId) {
        return jpaRepository.findByCustomerId(customerId);
    }

    @Override
    public List<OrderEntity> findByOrderDateAfter(LocalDateTime date) {
        return jpaRepository.findByOrderDateAfter(date);
    }

    @Override
    public OrderEntity buscarPorNumero(String orderNumber) {
        return jpaRepository.buscarPorNumero(orderNumber);
    }
}

