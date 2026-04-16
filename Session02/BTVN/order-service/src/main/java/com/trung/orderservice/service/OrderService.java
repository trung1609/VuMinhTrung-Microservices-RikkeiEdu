package com.trung.orderservice.service;

import com.trung.orderservice.dto.request.CreateOrderRequest;
import com.trung.orderservice.dto.request.UpdateOrderStatusRequest;
import com.trung.orderservice.dto.response.ApiResponse;
import com.trung.orderservice.dto.response.OrderDetailResponse;
import com.trung.orderservice.dto.response.OrderResponse;
import com.trung.orderservice.dto.response.UpdateOrderStatusResponse;
import com.trung.orderservice.exception.InvalidDataException;
import com.trung.orderservice.exception.ResourceNotFoundException;

public interface OrderService {
    ApiResponse<OrderResponse> createOrder(CreateOrderRequest request);
    ApiResponse<OrderDetailResponse> getOrderDetail(Long orderId) throws ResourceNotFoundException;
    ApiResponse<UpdateOrderStatusResponse> updateOrderStatus(Long orderId,UpdateOrderStatusRequest request) throws ResourceNotFoundException, InvalidDataException;
}
