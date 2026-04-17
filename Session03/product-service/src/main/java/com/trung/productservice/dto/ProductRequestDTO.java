package com.trung.productservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequestDTO {

    @NotBlank(message = "Product name is required")
    private String name;

    @Min(value = 1, message = "Stock quantity must be greater than 0")
    private Double price;

    @Min(value = 0, message = "Stock quantity cannot be negative")
    private Integer stockQuantity;

}
