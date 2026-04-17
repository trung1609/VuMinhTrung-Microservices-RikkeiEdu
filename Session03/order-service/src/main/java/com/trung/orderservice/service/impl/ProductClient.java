package com.trung.orderservice.service.impl;

import com.trung.orderservice.dto.ProductResponseDTO;
import com.trung.orderservice.exception.ServerErrorException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductClient {
    private final RestTemplate restTemplate;

    public ProductResponseDTO getProductById(Long productId) throws ServerErrorException {
        String url = "http://localhost:8082/api/v1/products/" + productId;

        try {
            return restTemplate.getForObject(url, ProductResponseDTO.class);
        }catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new ServerErrorException("PRODUCT_SERVICE_ERROR");
        }catch (ResourceAccessException e) {
            throw new ServerErrorException("PRODUCT_SERVICE_UNAVAILABLE");
        }
    }

    public void reduceStock(Long productId, Integer quantity) {
        String url = "http://localhost:8082/api/v1/products/reduce-stock"
                + "?productId=" + productId
                + "&quantity=" + quantity;

        restTemplate.put(url, null);
    }
}
