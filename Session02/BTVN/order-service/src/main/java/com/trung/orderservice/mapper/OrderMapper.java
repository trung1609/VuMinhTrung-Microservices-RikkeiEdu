package com.trung.orderservice.mapper;

import com.trung.orderservice.dto.response.OrderDetailResponse;
import com.trung.orderservice.dto.response.OrderResponse;
import com.trung.orderservice.entity.Orders;

public class OrderMapper {
    public static OrderResponse toDTO(Orders order) {
        return OrderResponse.builder()
                .id(order.getId())
                .status(order.getStatus())
                .totalAmount(order.getTotalAmount())
                .createdAt(order.getCreatedAt())
                .build();
    }

    public static OrderDetailResponse toDetailDTO(Orders order) {
        return OrderDetailResponse.builder()
                .id(order.getId())
                .userId(order.getUserId())
                .totalAmount(order.getTotalAmount())
                .status(order.getStatus())
                .items(order.getOrderItems().stream()
                        .map(i -> OrderDetailResponse.OrderItemResponse.builder()
                        .productId(i.getProductId())
                        .productName(i.getProductName())
                        .price(i.getPrice())
                        .quantity(i.getQuantity())
                        .build()).toList())
                .build();
    }
}
