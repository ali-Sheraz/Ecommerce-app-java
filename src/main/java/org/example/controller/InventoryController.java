package org.example.controller;


import org.example.entity.Inventory;
import org.example.service.InventoryService;
import org.example.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;
    private final ProductService productService;

    public InventoryController(InventoryService inventoryService, ProductService productService) {
        this.inventoryService = inventoryService;
        this.productService = productService;
    }



    @GetMapping("/{productId}")
    public ResponseEntity<Inventory> getInventoryByProduct(@PathVariable Long productId) {
        return productService.getProductById(productId)
                .flatMap(inventoryService::getInventoryByProduct)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventory(@PathVariable Long id) {
        inventoryService.deleteInventory(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping
    public ResponseEntity<List<Inventory>> getAllInventories() {
        List<Inventory> inventories = inventoryService.getAllInventories();
        return ResponseEntity.ok(inventories);
    }
    @GetMapping("/low-stock")
    public List<Inventory> getLowStock(@RequestParam(defaultValue = "10") int threshold) {
        return inventoryService.getLowStockProducts(threshold);
    }
    // Update inventory quantity
    @PutMapping("/update")
    public ResponseEntity<String> updateInventory(
            @RequestParam Long productId,
            @RequestParam int quantity,
            @RequestParam(defaultValue = "admin") String updatedBy
    ) {
        inventoryService.updateInventory(productId, quantity, updatedBy);
        return ResponseEntity.ok("Inventory updated successfully.");
    }

}
