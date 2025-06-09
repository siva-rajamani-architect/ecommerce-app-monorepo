package com.ecommerce.inventory.DTO;

import jakarta.persistence.Column;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record InventoryRequestDTO(
        Long id,
        String productCode,
        Integer quantity,
        BigDecimal unitPrice,
        LocalDateTime stockedAt,
        LocalDateTime lastUpdated
) {
}
