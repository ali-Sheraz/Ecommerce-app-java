package org.example.service;



import org.example.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product saveProduct(Product product);

    List<Product> getAllProducts();

    Optional<Product> getProductById(Long id);

    Product updateProduct(Long id, Product product);

    void deleteProduct(Long id);
}
