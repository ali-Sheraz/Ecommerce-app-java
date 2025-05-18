package org.example.service;



import org.example.entity.Sale;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface SalesService {

    List<Sale> getSalesByDateRange(LocalDateTime start, LocalDateTime end);
    double getRevenueForPeriod(String period, String start, String end);
    Map<String, Double> compareRevenues(LocalDateTime start1, LocalDateTime end1, LocalDateTime start2, LocalDateTime end2);
    List<Sale> getSalesByProduct(Long productId);
    List<Sale> getSalesByCategory(String category);

    Optional<Sale> getSaleById(Long id);

    void deleteSale(Long id);
}
