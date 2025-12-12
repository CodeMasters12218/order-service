package com.example.order;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import com.example.order.infrastructure.repository.OrderEntity;
import com.example.order.infrastructure.repository.OrderJpaRepository;
import com.example.order.domain.model.OrderStatus;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrderRepositoryCustomTest {

    @Autowired
    private OrderJpaRepository orderRepository;

    @Test
    void testFindByStatus() {
        OrderEntity order1 = new OrderEntity(null, "ORD-001", 123, LocalDateTime.now(), OrderStatus.PROCESADO, List.of());
        OrderEntity order2 = new OrderEntity(null, "ORD-002", 456, LocalDateTime.now(), OrderStatus.ENVIADO, List.of());
        orderRepository.save(order1);
        orderRepository.save(order2);

        List<OrderEntity> pendingOrders = orderRepository.findByStatus(OrderStatus.PROCESADO);

        assertThat(pendingOrders).hasSize(1);
        assertThat(pendingOrders.get(0).getOrderNumber()).isEqualTo("ORD-001");
    }

    @Test
    void testBuscarPorNumero() {
        OrderEntity order = new OrderEntity(null, "ORD-999", 789, LocalDateTime.now(), OrderStatus.PROCESADO, List.of());
        orderRepository.save(order);

        OrderEntity found = orderRepository.buscarPorNumero("ORD-999");

        assertThat(found).isNotNull();
        assertThat(found.getCustomerId()).isEqualTo(789);
    }
}
