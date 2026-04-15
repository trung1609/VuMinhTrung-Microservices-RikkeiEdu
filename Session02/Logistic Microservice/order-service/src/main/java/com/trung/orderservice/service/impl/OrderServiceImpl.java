package com.trung.orderservice.service.impl;

import com.trung.orderservice.constant.OrderStatus;
import com.trung.orderservice.dto.request.CreateOrderRequest;
import com.trung.orderservice.dto.request.OrderItemRequest;
import com.trung.orderservice.dto.request.UpdateOrderStatusRequest;
import com.trung.orderservice.dto.response.ApiResponse;
import com.trung.orderservice.dto.response.OrderDetailResponse;
import com.trung.orderservice.dto.response.OrderResponse;
import com.trung.orderservice.dto.response.UpdateOrderStatusResponse;
import com.trung.orderservice.entity.OrderItem;
import com.trung.orderservice.entity.OrderStatusHistory;
import com.trung.orderservice.entity.Orders;
import com.trung.orderservice.exception.InvalidDataException;
import com.trung.orderservice.exception.ResourceNotFoundException;
import com.trung.orderservice.mapper.OrderMapper;
import com.trung.orderservice.repository.OrderRepository;
import com.trung.orderservice.repository.OrderStatusRepository;
import com.trung.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderStatusRepository orderStatusRepository;


    @Override
    @Transactional
    public ApiResponse<OrderResponse> createOrder(CreateOrderRequest request) {

        Orders orders = new Orders();
        orders.setUserId(request.getUserId());
        orders.setStatus(OrderStatus.PENDING);

        Map<Long, OrderItem> items = new HashMap<>();

        for (OrderItemRequest itemRequest : request.getItems()) {
            if (items.containsKey(itemRequest.getProductId())) {
                OrderItem existingItem = items.get(itemRequest.getProductId());
                existingItem.setQuantity(existingItem.getQuantity() + itemRequest.getQuantity());
            }else {
                OrderItem orderItem = OrderItem.builder()
                        .productId(itemRequest.getProductId())
                        .quantity(itemRequest.getQuantity())
                        .price(itemRequest.getPrice())
                        .productName(itemRequest.getProductName())
                        .orderId(orders)
                        .build();
                items.put(itemRequest.getProductId(), orderItem);
            }
        }

        List<OrderItem> orderItems = new ArrayList<>(items.values());
        orderItems.forEach(i -> i.setOrderId(orders));
        BigDecimal total = BigDecimal.ZERO;
        for (OrderItem item : orderItems) {
            total = total.add(item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
        }
        orders.setTotalAmount(total);
        orders.setOrderItems(orderItems);
        orderRepository.save(orders);
        return ApiResponse.<OrderResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Order created successfully")
                .data(OrderMapper.toDTO(orders))
                .success(true)
                .build();
    }

    @Override
    public ApiResponse<OrderDetailResponse> getOrderDetail(Long orderId) throws ResourceNotFoundException {
        Orders orders = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + orderId));

        return ApiResponse.<OrderDetailResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Order detail retrieved successfully")
                .data(OrderMapper.toDetailDTO(orders))
                .success(true)
                .build();
    }

    @Override
    public ApiResponse<UpdateOrderStatusResponse> updateOrderStatus(Long orderId, UpdateOrderStatusRequest request) throws ResourceNotFoundException, InvalidDataException {
        Orders orders = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + orderId));

        try {
            orders.setStatus(OrderStatus.valueOf(request.getStatus().toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new InvalidDataException("Invalid order status: " + request.getStatus());
        }
        orderRepository.save(orders);
        OrderStatusHistory orderStatusHistory = OrderStatusHistory.builder()
                .orderId(orders)
                .status(com.trung.orderservice.constant.OrderStatusHistory.UPDATED)
                .build();

        orderStatusRepository.save(orderStatusHistory);

        return ApiResponse.<UpdateOrderStatusResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Order status updated successfully")
                .data(UpdateOrderStatusResponse.builder()
                        .id(orders.getId())
                        .status(orders.getStatus())
                        .build())
                .success(true)
                .build();
    }
}
