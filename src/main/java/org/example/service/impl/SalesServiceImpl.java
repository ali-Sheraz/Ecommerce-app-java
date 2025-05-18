package org.example.service.impl;

import jakarta.transaction.Transactional;
import org.example.entity.Inventory;
import org.example.entity.Sale;
import org.example.repository.InventoryRepository;
import org.example.repository.SalesRepository;
import org.example.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class SalesServiceImpl implements SalesService {
@Autowired
    private  SalesRepository salesRepository;
@Autowired
    private  InventoryRepository inventoryRepository;


    @Override
    public Sale createSale(Long productId, int quantitySold) {
        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Inventory not found for product id: " + productId));

        if (inventory.getQuantity() < quantitySold) {
            throw new RuntimeException("Not enough stock for product id: " + productId);
        }

        // Update inventory
        inventory.setQuantity(inventory.getQuantity() - quantitySold);
        inventory.setLastUpdated(LocalDateTime.now());
        inventoryRepository.save(inventory);

        // Create sale record
        Sale sale = new Sale();
        sale.setProduct(inventory.getProduct());
        sale.setQuantity(quantitySold);
        sale.setTotalAmount(inventory.getProduct().getPrice() * quantitySold);
        sale.setSaleDate(LocalDateTime.now());

        return salesRepository.save(sale);
    }

    @Override
    public List<Sale> getSalesByDateRange(LocalDateTime start, LocalDateTime end) {
        return salesRepository.findBySaleDateBetween(start, end);
    }

    @Override
    public double getRevenueForPeriod(String period, String start, String end) {
        LocalDateTime startDate, endDate;
        if (start != null && end != null) {
            startDate = LocalDate.parse(start).atStartOfDay();
            endDate = LocalDate.parse(end).atTime(23, 59, 59);
        } else {
            endDate = LocalDateTime.now();
            switch (period.toLowerCase()) {
                case "daily": startDate = endDate.minusDays(1); break;
                case "weekly": startDate = endDate.minusWeeks(1); break;
                case "monthly": startDate = endDate.minusMonths(1); break;
                case "yearly": startDate = endDate.minusYears(1); break;
                default: throw new IllegalArgumentException("Invalid period.");
            }
        }

        return salesRepository.findBySaleDateBetween(startDate, endDate)
                .stream()
                .mapToDouble(Sale::getTotalAmount)
                .sum();
    }

    @Override
    public Map<String, Double> compareRevenues(LocalDateTime start1, LocalDateTime end1, LocalDateTime start2, LocalDateTime end2) {
        double revenue1 = salesRepository.findBySaleDateBetween(start1, end1)
                .stream().mapToDouble(Sale::getTotalAmount).sum();
        double revenue2 = salesRepository.findBySaleDateBetween(start2, end2)
                .stream().mapToDouble(Sale::getTotalAmount).sum();
        return Map.of("period1", revenue1, "period2", revenue2);
    }
    @Override
    public List<Sale> getSalesByProduct(Long productId) {
        return salesRepository.findByProductId(productId);
    }

    @Override
    public Optional<Sale> getSaleById(Long id) {
        return salesRepository.findById(id);
    }
    @Override
    public List<Sale> getSalesByCategory(String category) {
        return salesRepository.findByProductCategory(category);
    }
    @Override
    public void deleteSale(Long id) {
        salesRepository.deleteById(id);
    }
}
