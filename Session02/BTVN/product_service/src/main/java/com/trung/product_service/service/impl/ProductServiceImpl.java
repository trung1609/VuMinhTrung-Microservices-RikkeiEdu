package com.trung.product_service.service.impl;

import com.trung.product_service.dto.ProductResponseDTO;
import com.trung.product_service.entity.Product;
import com.trung.product_service.mapper.ProductMapper;
import com.trung.product_service.repository.ProductRepository;
import com.trung.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream()
                .map(ProductMapper::toDTO)
                .toList();
    }

}
