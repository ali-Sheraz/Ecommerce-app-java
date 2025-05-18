package org.example.controller;


import org.example.entity.Sale;
import org.example.service.ProductService;
import org.example.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sales")
public class SalesController {
@Autowired
    private  SalesService salesService;

    @PostMapping("/create")
    public ResponseEntity<Sale> createSale(@RequestParam Long productId,
                                           @RequestParam int quantity) {
            Sale sale = salesService.createSale(productId, quantity);
            return ResponseEntity.ok(sale);

    }


    @GetMapping("/{id}")
    public ResponseEntity<Sale> getSaleById(@PathVariable Long id) {
        return salesService.getSaleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable Long id) {
        salesService.deleteSale(id);
        return ResponseEntity.noContent().build();
    }

    // Get revenue for a time period
    @GetMapping("/revenue")
    public double getRevenue(
            @RequestParam String period, // daily, weekly, monthly, yearly
            @RequestParam(required = false) String start,
            @RequestParam(required = false) String end
    ) {
        return salesService.getRevenueForPeriod(period, start, end);
    }

    // Compare revenue between two time periods
    @GetMapping("/compare")
    public Map<String, Double> compareRevenue(
            @RequestParam String start1,
            @RequestParam String end1,
            @RequestParam String start2,
            @RequestParam String end2
    ) {
        return salesService.compareRevenues(
                LocalDate.parse(start1).atStartOfDay(),
                LocalDate.parse(end1).atTime(23, 59, 59),
                LocalDate.parse(start2).atStartOfDay(),
                LocalDate.parse(end2).atTime(23, 59, 59)
        );
    }
    // Get sales by date range
    @GetMapping("/by-date")
    public List<Sale> getSalesByDateRange(@RequestParam String start, @RequestParam String end) {
        LocalDateTime startDate = LocalDate.parse(start).atStartOfDay();
        LocalDateTime endDate = LocalDate.parse(end).atTime(23, 59, 59);
        return salesService.getSalesByDateRange(startDate, endDate);
    }
    // Get sales by product
    @GetMapping("/by-product/{productId}")
    public List<Sale> getSalesByProduct(@PathVariable Long productId) {
        return salesService.getSalesByProduct(productId);
    }

    // Get sales by category
    @GetMapping("/by-category")
    public List<Sale> getSalesByCategory(@RequestParam String category) {
        return salesService.getSalesByCategory(category);
    }
}
