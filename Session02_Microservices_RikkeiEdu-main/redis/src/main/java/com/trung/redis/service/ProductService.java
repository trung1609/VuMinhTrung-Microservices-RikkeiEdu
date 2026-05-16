package com.trung.redis.service;

import com.trung.redis.dto.ApiResponse;
import com.trung.redis.dto.ProductDto;
import com.trung.redis.entity.Product;
import com.trung.redis.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final RedisTemplate<String, Product> redisTemplate;

    public ApiResponse getProductById(String desc) {
        long start = System.nanoTime();
        Product product = productRepository.findByDescriptionNative(desc).orElseThrow(() -> new RuntimeException("Product not found with id: " + desc));
        long end = System.nanoTime();
        return ApiResponse.builder()
                .duration((end - start)/1000000)
                .data(ProductDto.mapToDto(product))
                .build();
    }

    @Cacheable(value = "product:", key = "#desc")
    public ApiResponse getProductByIdWithRedis(String desc) {
//        long start = System.nanoTime();
//        String key = "product:" + desc;
//        Product value = redisTemplate.opsForValue().get(key);
//        if (value != null) {
//            long end = System.nanoTime();
//            return ApiResponse.builder()
//                    .duration((end - start)/1000000)
//                    .data(ProductDto.mapToDto(value))
//                    .build();
//        }
//        Product product = productRepository.findByDescriptionNative(desc).orElseThrow(() -> new RuntimeException("Product not found with id: " + desc));
//        redisTemplate.opsForValue().set(key, product, Duration.ofMinutes(5));
//        long end = System.nanoTime();
//        return ApiResponse.builder()
//                .duration((end - start)/1000000)
//                .data(ProductDto.mapToDto(product))
//                .build();
        // neu cacheable ko tim duoc key tuong ung thi se return ve gia tri se luu ve cache
        Product product = productRepository.findByDescriptionNative(desc).orElseThrow(() -> new RuntimeException("Product not found with id: " + desc));
        return ApiResponse.builder()
                .duration(10)
                .data(ProductDto.mapToDto(product))
                .build();
    }
}
