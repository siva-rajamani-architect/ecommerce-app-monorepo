package com.ecommerce.inventory.controller;

import com.ecommerce.inventory.DTO.InventoryRequestDTO;
import com.ecommerce.inventory.model.Inventory;
import com.ecommerce.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping
    public ResponseEntity<Inventory> createInventory(@RequestBody InventoryRequestDTO inventoryRequestDTO) {
        return ResponseEntity.ok(inventoryService.save(inventoryRequestDTO));
    }

    @PutMapping
    public ResponseEntity<Inventory> updateInventory(@RequestBody InventoryRequestDTO inventoryRequestDTO) {
        return ResponseEntity.ok(inventoryService.update(inventoryRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventory(@PathVariable Long id) {
        inventoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Inventory>> getAllInventories() {
        return new ResponseEntity<>(inventoryService.getAllInventories(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventory> getInventoryById( @PathVariable Long id) {
        return new ResponseEntity<>(inventoryService.getById(id), HttpStatus.OK);
    }
}
