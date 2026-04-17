package com.trung.orderservice.service;

import com.trung.orderservice.dto.OrderCreateRequest;
import com.trung.orderservice.dto.OrderResponse;
import com.trung.orderservice.exception.InvalidDataException;
import com.trung.orderservice.exception.ResourceNotFoundException;
import com.trung.orderservice.exception.ServerErrorException;

public interface OrderService {
    OrderResponse createOrder(OrderCreateRequest request) throws InvalidDataException, ServerErrorException;
    OrderResponse getOrderById(Long id) throws ResourceNotFoundException;
}
