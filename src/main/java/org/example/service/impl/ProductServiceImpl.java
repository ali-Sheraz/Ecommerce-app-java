package org.example.service.impl;


import jakarta.transaction.Transactional;
import org.example.entity.Inventory;
import org.example.entity.Product;
import org.example.repository.InventoryRepository;
import org.example.repository.ProductRepository;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
@Autowired
    private  ProductRepository productRepository;
@Autowired
    private  InventoryRepository inventoryRepository;



    @Override
    public Product saveProduct(Product product) {
        product.setCreatedAt(LocalDateTime.now());
        Product savedProduct = productRepository.save(product);  // save product first to get ID

        Inventory inventory = new Inventory();
        inventory.setProduct(savedProduct);
        inventory.setQuantity(20);  // or some initial stock number
        inventory.setLastUpdated(LocalDateTime.now());
        inventoryRepository.save(inventory);  // You need to inject InventoryRepository here!

        return savedProduct;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product updateProduct(Long id, Product updatedProduct) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(updatedProduct.getName());
                    product.setCategory(updatedProduct.getCategory());
                    product.setPrice(updatedProduct.getPrice());
                    return productRepository.save(product);
                })
                .orElseThrow(() -> new RuntimeException("Product not found with id " + id));
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
