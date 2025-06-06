package com.ecommerce.inventory.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "inventory")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inventory {

    @Id
    private Long id;
    private String productCode;
    private Integer quantity;
    private Boolean inStock;
    private Integer unitprice; 
}
