package com.example.order.infrastructure.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.common.dto.ProductDTO;

@FeignClient(name = "product-service")
public interface ProductsClient {

    @GetMapping("/api/products/{id}")
    ProductDTO obtenerProductoPorId(@PathVariable("id") Integer id);
}
