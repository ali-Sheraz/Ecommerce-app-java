package org.example.service.impl;

import jakarta.transaction.Transactional;
import org.example.entity.Inventory;
import org.example.entity.InventoryLog;
import org.example.entity.Product;
import org.example.repository.InventoryLogRepository;
import org.example.repository.InventoryRepository;
import org.example.repository.ProductRepository;
import org.example.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    private  InventoryRepository inventoryRepository;

    @Autowired
    private InventoryRepository inventoryRepo;


    @Autowired
    private InventoryLogRepository logRepo;

    @Override
    public Optional<Inventory> getInventoryByProduct(Product product) {
        return inventoryRepository.findByProduct(product);
    }

    @Override
    public List<Inventory> getAllInventories() {
        return inventoryRepository.findAll();
    }

    @Override
    public void deleteInventory(Long id) {
        inventoryRepository.deleteById(id);
    }
    @Override
    public List<Inventory> getLowStockProducts(int threshold) {
        return inventoryRepo.findByQuantityLessThanEqual(threshold);
    }
    @Override
    public void updateInventory(Long productId, int newQuantity, String updatedBy) {
        Inventory inventory = inventoryRepo.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));

        InventoryLog log = new InventoryLog();
        log.setProduct(inventory.getProduct());
        log.setPreviousQuantity(inventory.getQuantity());
        log.setUpdatedQuantity(newQuantity);
        log.setTimestamp(LocalDateTime.now());
        log.setUpdatedBy(updatedBy);

        logRepo.save(log);

        inventory.setQuantity(newQuantity);
        inventory.setLastUpdated(LocalDateTime.now());
        inventoryRepo.save(inventory);
    }
}
