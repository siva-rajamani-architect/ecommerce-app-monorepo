package com.ecommerce.product.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductResponseDTO(
        Long id,
        String productCode,
        String name,
        String description,
        String brand,
        String category,
        BigDecimal price,
        String status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
