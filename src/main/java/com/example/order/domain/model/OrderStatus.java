package com.example.order.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Possible statuses of an order.")
public enum OrderStatus {
    PROCESADO,
    ENVIADO
}
