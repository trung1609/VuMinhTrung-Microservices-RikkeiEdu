package com.trung.orderservice.dto.response;

import com.trung.orderservice.constant.OrderStatus;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailResponse {
    private Long id;
    private Long userId;
    private OrderStatus status;
    private BigDecimal totalAmount;
    private List<OrderItemResponse> items;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class OrderItemResponse{
        private Long productId;
        private String productName;
        private BigDecimal price;
        private Integer quantity;
    }
}
