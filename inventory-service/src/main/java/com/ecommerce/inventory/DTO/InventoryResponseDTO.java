package com.ecommerce.inventory.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record InventoryResponseDTO(
        Long id,
        String productCode,
        Integer quantity,
        BigDecimal unitPrice,
        LocalDateTime stockedAt,
        LocalDateTime lastUpdated
) {
}
