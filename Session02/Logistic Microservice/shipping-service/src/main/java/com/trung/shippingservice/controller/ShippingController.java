package com.trung.shippingservice.controller;

import com.trung.shippingservice.dto.*;
import com.trung.shippingservice.exception.ResourceNotFoundException;
import com.trung.shippingservice.service.ShippingService;
import com.trung.shippingservice.service.impl.OrderOpenFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/shipments")
@RequiredArgsConstructor
@RefreshScope
public class ShippingController {
    private final ShippingService shippingService;
    private final RestTemplate restTemplate;
    private final OrderOpenFeign orderOpenFeign;

    @Value("${test.username}")
    private String username;

    @PostMapping("/create-order")
    public ResponseEntity<ApiResponse<OrderResponse>> createOrder(@RequestBody CreateOrderRequest request) {
        String url = "http://ORDER-SERVICE/api/v1/orders";
        ParameterizedTypeReference<ApiResponse<OrderResponse>> responseType =
                new ParameterizedTypeReference<ApiResponse<OrderResponse>>() {};

        ResponseEntity<ApiResponse<OrderResponse>> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(request),
                responseType
        );

        // get delete gửi tham số trên đường dẫn
        // post put patch gửi tham số vào body

        return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.CREATED);
    }

    @GetMapping("/show-order/{orderId}")
    public ResponseEntity<OrderDetailResponse> getOrderDetail(@PathVariable Long orderId) throws ResourceNotFoundException {
        return new ResponseEntity<>(orderOpenFeign.getOrderDetail(orderId).getBody().getData(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ShippingCreateResponse> createShipment(@RequestBody ShippingCreateRequest request) throws ResourceNotFoundException {
        return new ResponseEntity<>(shippingService.createShipment(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShippingResponse> getShipment(@PathVariable Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(shippingService.getShipment(id), HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<Map<String,String>> test() {
        return new ResponseEntity<>(Map.of("username", username), HttpStatus.OK);
    }
}
