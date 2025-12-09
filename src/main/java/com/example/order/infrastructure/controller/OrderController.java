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

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderServicePort service;

    public OrderController(OrderServicePort service) { this.service = service; }

    @Operation(summary = "Get all orders", description = "Returns a list of all orders.")
    @GetMapping
    public List<Order> findAll() { return service.findAll(); }

    @Operation(summary = "Create a new order", description = "Saves a new order with items.")
    @PostMapping
    public Order save(@RequestBody Order order) { return service.save(order); }

    @Operation(summary = "Find order by ID", description = "Returns an order by its unique identifier.")
    @GetMapping("/{id}")
    public Order findById(@PathVariable Integer id) { return service.findById(id); }

    @Operation(summary = "Update order", description = "Updates an existing order by ID.")
    @PutMapping("/{id}")
    public ResponseEntity<Order> update(@PathVariable Integer id, @RequestBody Order order) {
        return ResponseEntity.ok(service.update(id, order));
    }

    @Operation(summary = "Delete order", description = "Deletes an order by ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Add item to order", description = "Adds a new item to an existing order.")
    @PostMapping("/{id}/items")
    public ResponseEntity<Order> addItem(@PathVariable Integer id, @RequestBody OrderItem item) {
        return ResponseEntity.ok(service.addItem(id, item));
    }

    @Operation(summary = "Get items of order", description = "Returns all items of a specific order.")
    @GetMapping("/{id}/items")
    public List<OrderItem> getItems(@PathVariable Integer id) { return service.getItems(id); }

    @Operation(summary = "Get orders by status", description = "Returns orders filtered by status.")
    @GetMapping("/estado/{status}")
    public List<OrderEntity> getByStatus(@PathVariable OrderStatus status) {
        return service.findByStatus(status);
    }

    @Operation(summary = "Get orders by customer", description = "Returns orders placed by a specific customer.")
    @GetMapping("/cliente/{customerId}")
    public List<OrderEntity> getByCustomer(@PathVariable Integer customerId) {
        return service.findByCustomerId(customerId);
    }

    @Operation(summary = "Get recent orders", description = "Returns orders placed after a given date.")
    @GetMapping("/recientes/{fecha}")
    public List<OrderEntity> getRecentOrders(@PathVariable String fecha) {
        return service.findByOrderDateAfter(LocalDateTime.parse(fecha));
    }

    @Operation(summary = "Get order by number", description = "Finds an order by its order number.")
    @GetMapping("/numero/{orderNumber}")
    public OrderEntity getByOrderNumber(@PathVariable String orderNumber) {
        return service.buscarPorNumero(orderNumber);
    }
}
