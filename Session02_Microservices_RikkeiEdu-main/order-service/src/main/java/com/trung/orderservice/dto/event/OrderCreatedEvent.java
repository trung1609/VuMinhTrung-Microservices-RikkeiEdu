package com.trung.orderservice.dto.event;

import com.trung.orderservice.dto.request.OrderItemRequest;
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
    private Long orderId;           // Để gửi vận chuyển
    private Long userId;            // Để tracking
    private String userEmail;       // Để gửi mail
    private List<OrderItemRequest> items; // Để vận chuyển xử lý
    private BigDecimal totalAmount;  // Thông tin đơn hàng
}

