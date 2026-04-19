package com.trung.product_service.mapper;


import com.trung.product_service.dto.ProductResponseDTO;
import com.trung.product_service.entity.Product;

public class ProductMapper {
    public static ProductResponseDTO toDTO(Product product) {
        return ProductResponseDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .sellPrice(product.getSellPrice())
                .build();
    }
}
