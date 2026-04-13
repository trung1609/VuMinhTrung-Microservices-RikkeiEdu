package com.api.trung.service.impl;

import com.api.trung.entity.Product;
import com.api.trung.repository.ProductRepository;
import com.api.trung.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product findProductById(Long id) throws Exception {
        return productRepository.findById(id).orElseThrow(() -> new Exception("Product not found"));
    }

    @Override
    public Product updateProduct(Long id, Product product) throws Exception {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new Exception("Product not found"));
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setStock(product.getStock());
        existingProduct.setImageUrl(product.getImageUrl());
        existingProduct.setIsActive(product.getIsActive());

        return productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(Long id) throws Exception {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new Exception("Product not found"));
        productRepository.delete(existingProduct);
    }
}
