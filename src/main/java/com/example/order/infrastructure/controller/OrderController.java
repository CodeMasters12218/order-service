package com.example.order.infrastructure.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.order.domain.model.Order;
import com.example.order.domain.model.OrderItem;
import com.example.order.domain.model.OrderStatus;
import com.example.order.domain.ports.OrderServicePort;
import com.example.order.infrastructure.repository.OrderEntity;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderServicePort service;

    public OrderController(OrderServicePort service) {
        this.service = service;
    }

    @GetMapping
    public List<Order> findAll() { return service.findAll(); }

    @PostMapping
    public Order save(@RequestBody Order order) { return service.save(order); }

    @GetMapping("/{id}")
    public Order findById(@PathVariable Integer id) { return service.findById(id); }

    @PutMapping("/{id}")
    public ResponseEntity<Order> update(@PathVariable Integer id, @RequestBody Order order) {
        Order updated = service.update(id, order);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // --- Sub-endpoints para Items ---
    @PostMapping("/{id}/items")
    public ResponseEntity<Order> addItem(@PathVariable Integer id, @RequestBody OrderItem item) {
        Order updated = service.addItem(id, item);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/{id}/items")
    public List<OrderItem> getItems(@PathVariable Integer id) {
        return service.getItems(id);
    }

    @GetMapping("/estado/{status}")
    public List<OrderEntity> getByStatus(@PathVariable OrderStatus status) {
        return service.findByStatus(status);
    }

    @GetMapping("/cliente/{customerId}")
    public List<OrderEntity> getByCustomer(@PathVariable Integer customerId) {
        return service.findByCustomerId(customerId);
    }

    @GetMapping("/recientes/{fecha}")
    public List<OrderEntity> getRecentOrders(@PathVariable String fecha) {
        LocalDateTime date = LocalDateTime.parse(fecha);
        return service.findByOrderDateAfter(date);
    }

    @GetMapping("/numero/{orderNumber}")
    public OrderEntity getByOrderNumber(@PathVariable String orderNumber) {
        return service.buscarPorNumero(orderNumber);
    }
}
