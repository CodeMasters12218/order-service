package com.example.order.domain.model;

import java.time.LocalDateTime;
import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Represents a customer order with items and status.")
public class Order {
    @Schema(description = "Unique identifier of the order", example = "101")
    private Integer id;

    @Schema(description = "Order number assigned to the purchase", example = "ORD-2025-001")
    private String orderNumber;

    @Schema(description = "Identifier of the customer who placed the order", example = "55")
    private Integer customerId;

    @Schema(description = "Date and time when the order was created", example = "2025-12-03T15:30:00")
    private LocalDateTime orderDate;

    @Schema(description = "Current status of the order", example = "PROCESADO")
    private OrderStatus status;

    @Schema(description = "List of items included in the order")
    private List<OrderItem> items;

    @Schema(description = "Total amount for the order", example = "199.99")
    private Double totalAmount;
    
    public Order() {}
    
    public Order(Integer id, String orderNumber, Integer customerId, LocalDateTime orderDate, OrderStatus status,
            List<OrderItem> items, Double totalAmount) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.status = status;
        this.items = items;
        this.totalAmount = totalAmount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

}
