package com.trung.product_service;

import com.trung.product_service.entity.Product;
import com.trung.product_service.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(ProductRepository productRepository){
        return args -> {
            productRepository.save(
                    new Product(
                            "Laptop Dell",
                            "SKU001",
                            new BigDecimal("15000000"),
                            new BigDecimal("18000000"),
                            10
                    )
            );

            productRepository.save(
                    new Product(
                            "Chuột Logitech",
                            "SKU002",
                            new BigDecimal("200000"),
                            new BigDecimal("350000"),
                            50
                    )
            );
        };
    }
}
