package org.example.service;



import org.example.entity.Inventory;
import org.example.entity.Product;

import java.util.List;
import java.util.Optional;

public interface InventoryService {


    Optional<Inventory> getInventoryByProduct(Product product);

    List<Inventory> getAllInventories();

    void deleteInventory(Long id);

    List<Inventory> getLowStockProducts(int threshold);
    void updateInventory(Long productId, int newQuantity, String updatedBy);
}
