package com.ecommerce.product.DTO;

public record ProductRequestDTO(
        Long id,
        String name,
        String description,
        double price,
        String category,
        String productCode
) {
}
