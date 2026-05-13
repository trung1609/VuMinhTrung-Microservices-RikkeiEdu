package com.example.emailservice.event;

import com.example.emailservice.OrderItemRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderCreatedEvent {
    private Long orderId;
    private Long userId;
    private String userEmail;
    private List<OrderItemRequest> items;
    private BigDecimal totalAmount;
}

