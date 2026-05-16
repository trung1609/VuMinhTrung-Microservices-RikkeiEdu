package com.trung.orderservice.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse<T>{
    private T data;
    private String message;
    private boolean success;
    private Integer statusCode;
}
