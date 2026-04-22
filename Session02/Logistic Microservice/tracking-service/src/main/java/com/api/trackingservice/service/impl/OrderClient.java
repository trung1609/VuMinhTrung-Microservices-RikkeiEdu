package com.api.trackingservice.service.impl;


import com.api.trackingservice.dto.ApiResponse;
import com.api.trackingservice.dto.OrderDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class OrderClient {
    private final RestTemplate restTemplate;

    public ResponseEntity<ApiResponse<OrderDetailResponse>> getOrderById(Long orderId) {
        String url = "http://localhost:8080/api/v1/orders/" + orderId;
        try {
            ParameterizedTypeReference<ApiResponse<OrderDetailResponse>> responseType =
                    new ParameterizedTypeReference<>() {};

            return restTemplate.exchange(url, HttpMethod.GET, null, responseType);
        } catch (HttpClientErrorException.NotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
