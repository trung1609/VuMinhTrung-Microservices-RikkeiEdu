package com.trung.orderservice.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOrderRequest {

    @NotNull( message = "User id is required")
    private Long userId;

    @Email(message = "User email must be valid")
    @NotNull(message = "User email is required")
    private String userEmail;

    @Valid
    @NotNull( message = "Items is required")
    private List<OrderItemRequest> items;
}
