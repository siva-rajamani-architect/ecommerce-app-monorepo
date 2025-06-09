package com.ecommerce.inventory.mapper;

import com.ecommerce.inventory.DTO.InventoryRequestDTO;
import com.ecommerce.inventory.DTO.InventoryResponseDTO;
import com.ecommerce.inventory.model.Inventory;

public class InventoryMapper {

    public static InventoryResponseDTO mapInventoryToInventoryResponse(Inventory inventory) {
        return new InventoryResponseDTO(inventory.getId(),
                inventory.getProductCd(), inventory.getQuantity(),
                inventory.getUnitPrice(), inventory.getStockedAt(),
                inventory.getLastUpdated());
    }

    public static Inventory mapInventoryRequestToInventory(InventoryRequestDTO inventoryRequest) {
        return Inventory.builder()
                .productCd(inventoryRequest.productCode())
                .quantity(inventoryRequest.quantity())
                .unitPrice(inventoryRequest.unitPrice())
                .stockedAt(inventoryRequest.stockedAt())
                .lastUpdated(inventoryRequest.lastUpdated())
                .build();
     }
}
