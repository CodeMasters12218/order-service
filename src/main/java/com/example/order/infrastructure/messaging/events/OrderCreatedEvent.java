package com.example.order.infrastructure.messaging.events;

import java.time.LocalDateTime;
import java.util.List;

public class OrderCreatedEvent {
    private String orderId;
    private String userId;
    private List<OrderItem> items;
    private Double totalAmount;
    private LocalDateTime createdAt;
    public OrderCreatedEvent(String orderId, String userId, List<OrderItem> items, Double totalAmount,
            LocalDateTime createdAt) {
        this.orderId = orderId;
        this.userId = userId;
        this.items = items;
        this.totalAmount = totalAmount;
        this.createdAt = createdAt;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
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
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}



