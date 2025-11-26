package com.example.order.infrastructure.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.order.domain.model.OrderStatus;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, Integer> {
    List<OrderEntity> findByStatus(OrderStatus status);

    List<OrderEntity> findByCustomerId(Integer customerId);
    
    List<OrderEntity> buscarPedidosRecientes(@Param("fecha") LocalDateTime fecha);

    @Query("SELECT o FROM OrderEntity o WHERE o.orderNumber = :orderNumber")
    OrderEntity buscarPorNumero(@Param("orderNumber") String orderNumber);
}
