package org.example.repository;


import org.example.entity.Inventory;
import org.example.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Optional<Inventory> findByProduct(Product product);

    List<Inventory> findByQuantityLessThanEqual(int threshold);

    Optional<Inventory> findByProductId(Long productId);
}
