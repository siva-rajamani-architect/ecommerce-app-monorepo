package com.ecommerce.inventory.service;

import com.ecommerce.inventory.DTO.InventoryRequestDTO;
import com.ecommerce.inventory.exception.InvalidInventoryDataException;
import com.ecommerce.inventory.mapper.InventoryMapper;
import com.ecommerce.inventory.model.Inventory;
import com.ecommerce.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final RestTemplate restTemplate;

    public Inventory save(InventoryRequestDTO inventoryRequestDTO) {
        validate(inventoryRequestDTO);

        Boolean  existingProduct = restTemplate
                .getForObject("http://localhost:8081/api/v1/products/code/{productCode}" ,
                         Boolean.class, inventoryRequestDTO.productCode());
        if (Boolean.FALSE.equals(existingProduct)) {
            throw new InvalidInventoryDataException("Inventory with product code " + inventoryRequestDTO.productCode() + " NOT exists.");
        }

        Inventory inventory = InventoryMapper.mapInventoryRequestToInventory(inventoryRequestDTO);
        inventory.setStockedAt(LocalDateTime.now());
        return inventoryRepository.save(inventory);
    }

    public Inventory update(InventoryRequestDTO inventoryRequestDTO) {
        Inventory inventory = inventoryRepository.findById(inventoryRequestDTO.id()).orElseThrow(() -> new InvalidInventoryDataException("Inventory with id " + inventoryRequestDTO.id() + " not found."));
        if (inventory != null) {
            inventory.setProductCd(inventoryRequestDTO.productCode());
            inventory.setUnitPrice(inventoryRequestDTO.unitPrice());
            inventory.setQuantity(inventoryRequestDTO.quantity());
            inventory.setLastUpdated(LocalDateTime.now());
            return inventoryRepository.save(inventory);
        }
        return null;

    }
    public void delete(Long id) {
        inventoryRepository.findById(id).orElseThrow(() -> new InvalidInventoryDataException("Inventory with id " + id + " not found."));
        inventoryRepository.deleteById(id);
    }

    public Inventory getById(Long id) {
        return inventoryRepository.findById(id).orElseThrow(() -> new InvalidInventoryDataException("Inventory with id " + id + " not found."));
    }
    public List<Inventory> getAllInventories() {
        return inventoryRepository.findAll();
    }
    private void validate(InventoryRequestDTO inventoryRequestDTO) {
         if (inventoryRequestDTO.productCode().isEmpty() || inventoryRequestDTO.productCode().isBlank()) {
            throw new InvalidInventoryDataException("Product code cannot be empty or blank.");
        }
        if (inventoryRequestDTO.quantity() <= 0) {
            throw new InvalidInventoryDataException("Quantity must be greater than zero.");
        }
        if (inventoryRequestDTO.unitPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidInventoryDataException("Unit price must be greater than zero.");
        }
    }
}
