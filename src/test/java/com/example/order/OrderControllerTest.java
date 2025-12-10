package com.example.order;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.order.domain.model.Order;
import com.example.order.domain.ports.OrderServicePort;
import com.example.order.infrastructure.controller.OrderController;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import java.util.Collections;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private OrderServicePort orderService;

    @Test
    void testGetOrderById() throws Exception {
        Order order = new Order();
        order.setId(1);
        order.setOrderNumber("ORD-001");

        when(orderService.findById(1)).thenReturn(order);

        mockMvc.perform(get("/api/orders/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").value(1))
               .andExpect(jsonPath("$.orderNumber").value("ORD-001"));
    }

    @Test
    void testCreateOrder() throws Exception {
        Order order = new Order();
        order.setId(1);
        order.setOrderNumber("ORD-001");

        when(orderService.save(any(Order.class))).thenReturn(order);

        String jsonPayload = """
            {
              "id": 1,
              "orderNumber": "ORD-001",
              "customerId": 100
            }
            """;

        mockMvc.perform(post("/api/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPayload))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").value(1))
               .andExpect(jsonPath("$.orderNumber").value("ORD-001"));
    }

    @Test
    void testUpdateOrder() throws Exception {
        Order updatedOrder = new Order();
        updatedOrder.setId(1);
        updatedOrder.setOrderNumber("ORD-002");

        when(orderService.update(eq(1), any(Order.class))).thenReturn(updatedOrder);

        String jsonPayload = """
            {
              "id": 1,
              "orderNumber": "ORD-002",
              "customerId": 200
            }
            """;

        mockMvc.perform(put("/api/orders/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPayload))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.orderNumber").value("ORD-002"));
    }

    @Test
    void testDeleteOrder() throws Exception {
        doNothing().when(orderService).deleteById(1);

        mockMvc.perform(delete("/api/orders/1"))
               .andExpect(status().isNoContent());

        verify(orderService).deleteById(1);
    }

    @Test
    void testGetAllOrders() throws Exception {
        Order order = new Order();
        order.setId(1);
        order.setOrderNumber("ORD-001");

        when(orderService.findAll()).thenReturn(Collections.singletonList(order));

        mockMvc.perform(get("/api/orders"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].orderNumber").value("ORD-001"));
    }
}
