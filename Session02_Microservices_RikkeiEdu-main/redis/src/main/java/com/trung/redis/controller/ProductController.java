package com.trung.redis.controller;

import com.trung.redis.dto.ApiResponse;
import com.trung.redis.entity.Product;
import com.trung.redis.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ApiResponse getProductById(@RequestParam String id) {
        return productService.getProductById(id);
    }

    @GetMapping("/redis")
    public ApiResponse getProductByIdWithRedis(@RequestParam String id) {
        return productService.getProductByIdWithRedis(id);
    }
}
