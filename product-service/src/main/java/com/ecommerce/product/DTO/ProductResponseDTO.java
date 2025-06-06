package com.ecommerce.product.DTO;

public record ProductResponseDTO(
         Long id,
         String name,
         String description,
         double price,
         String category

) {
}
