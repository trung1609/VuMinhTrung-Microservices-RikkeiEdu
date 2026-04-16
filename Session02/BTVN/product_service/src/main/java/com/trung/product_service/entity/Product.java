package com.trung.product_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal importPrice;
    private BigDecimal sellPrice;
    private Integer stockQuantity;
    private String sku;

    public Product(String name, String sku, BigDecimal importPrice, BigDecimal sellPrice, Integer stockQuantity) {
        this.name = name;
        this.sku = sku;
        this.importPrice = importPrice;
        this.sellPrice = sellPrice;
        this.stockQuantity = stockQuantity;
    }
}
