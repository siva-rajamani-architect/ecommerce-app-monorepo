package com.ecommerce.product.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_code", unique = true, nullable = false, length = 30)
    private String productCode;

    @Column(nullable = false)
    private String name;

    @Column(length = 1000)
    private String description;

    private String brand;

    private String category;

    @Column(precision = 10, scale = 2)
    private BigDecimal price;

    private String status; // ACTIVE / INACTIVE

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();


}
