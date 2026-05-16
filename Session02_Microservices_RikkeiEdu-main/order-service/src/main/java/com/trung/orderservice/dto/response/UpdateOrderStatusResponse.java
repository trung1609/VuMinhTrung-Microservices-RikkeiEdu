package com.trung.orderservice.dto.response;

import com.trung.orderservice.constant.OrderStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateOrderStatusResponse {
    private Long id;
    private OrderStatus status;
}
