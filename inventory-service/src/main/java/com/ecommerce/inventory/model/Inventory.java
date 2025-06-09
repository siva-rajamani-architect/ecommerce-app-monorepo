package com.ecommerce.inventory.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "inventory")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productCd;
    private Integer quantity;
    @Column(name = "unit_price", precision = 10, scale = 2, nullable = false)
    private BigDecimal unitPrice;
    @Column(name = "stocked_at")
    private LocalDateTime stockedAt = LocalDateTime.now();
    @Column(name = "last_updated")
    private LocalDateTime lastUpdated = LocalDateTime.now();

}
