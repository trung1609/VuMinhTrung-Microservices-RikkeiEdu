package com.api.trung.service;

import com.api.trung.entity.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);
    List<Product> findAllProducts();
    Product findProductById(Long id) throws Exception;
    Product updateProduct(Long id, Product product) throws Exception;
    void deleteProduct(Long id) throws Exception;
}
