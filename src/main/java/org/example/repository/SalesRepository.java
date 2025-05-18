package org.example.repository;


import org.example.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SalesRepository extends JpaRepository<Sale, Long> {

    List<Sale> findBySaleDateBetween(LocalDateTime start, LocalDateTime end);

    List<Sale> findByProductId(Long productId);

    List<Sale> findByProductCategory(String category);

}
