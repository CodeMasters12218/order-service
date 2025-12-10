package com.example.order;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import com.example.order.infrastructure.repository.OrderEntity;
import com.example.order.infrastructure.repository.OrderJpaRepository;
import com.example.order.domain.model.OrderStatus;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

class OrderRepositoryTest {

    @Autowired
    private OrderJpaRepository orderRepository;

    @Test
    void testSaveAndFindById() {
        OrderEntity order = new OrderEntity();
        order.setOrderNumber("ORD-001");
        order.setCustomerId(123);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PROCESADO);

        OrderEntity saved = orderRepository.save(order);

        assertThat(saved.getId()).isNotNull();

        OrderEntity found = orderRepository.findById(saved.getId()).orElse(null);
        assertThat(found).isNotNull();
        assertThat(found.getOrderNumber()).isEqualTo("ORD-001");
    }
}
