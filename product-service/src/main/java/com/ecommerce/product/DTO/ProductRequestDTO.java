package com.ecommerce.product.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductRequestDTO(
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

/*
        Long id,
        String name,
        String description,
        double price,
        String category,
        String productCode

 */