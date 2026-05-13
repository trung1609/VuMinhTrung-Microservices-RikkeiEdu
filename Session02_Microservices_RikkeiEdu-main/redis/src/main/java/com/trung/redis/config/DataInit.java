package com.trung.redis.config;

import com.trung.redis.entity.Product;
import com.trung.redis.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

//@Configuration
@RequiredArgsConstructor
public class DataInit {
    private final ProductRepository productRepository;

//    @Bean
    public CommandLineRunner runner() {
        return args -> {
            List<Product> products = new ArrayList<>();
            // 10000 sản phẩm mẫu
            for (int i = 1; i <= 1000000; i++) {
                Product product = new Product(
                        null,
                        "Product name " + i,
                        "Description " + i,
                        1000.0 + i
                );
                products.add(product);
            }
            productRepository.saveAll(products);
            System.out.println("Insert successfully!");
        };
    }
}
