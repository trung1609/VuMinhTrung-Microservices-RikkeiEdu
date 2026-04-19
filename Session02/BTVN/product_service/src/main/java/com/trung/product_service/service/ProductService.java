package com.trung.product_service.service;

import com.trung.product_service.dto.ProductResponseDTO;

import java.util.List;

public interface ProductService {
    List<ProductResponseDTO> getAllProducts();
}
