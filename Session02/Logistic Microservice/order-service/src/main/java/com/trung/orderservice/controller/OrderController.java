package com.trung.orderservice.controller;

import com.trung.orderservice.dto.request.CreateOrderRequest;
import com.trung.orderservice.dto.request.UpdateOrderStatusRequest;
import com.trung.orderservice.dto.response.ApiResponse;
import com.trung.orderservice.dto.response.OrderDetailResponse;
import com.trung.orderservice.dto.response.OrderResponse;
import com.trung.orderservice.dto.response.UpdateOrderStatusResponse;
import com.trung.orderservice.exception.InvalidDataException;
import com.trung.orderservice.exception.ResourceNotFoundException;
import com.trung.orderservice.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<ApiResponse<OrderResponse>> createOrder(@Valid @RequestBody CreateOrderRequest request) {
        return new ResponseEntity<>(orderService.createOrder(request), HttpStatus.CREATED);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<ApiResponse<OrderDetailResponse>> getOrderDetail(@PathVariable Long orderId) throws ResourceNotFoundException {
        return new ResponseEntity<>(orderService.getOrderDetail(orderId), HttpStatus.OK);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<ApiResponse<UpdateOrderStatusResponse>> updateOrderStatus(@PathVariable Long orderId,
                                                                                    @Valid @RequestBody UpdateOrderStatusRequest request) throws ResourceNotFoundException, InvalidDataException {
        return new ResponseEntity<>(orderService.updateOrderStatus(orderId, request), HttpStatus.OK);
    }
}
