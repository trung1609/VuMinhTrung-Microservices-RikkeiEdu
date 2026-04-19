package com.trung.userservice.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiErrorResponse {
    private Object error;
    private String message;
    private LocalDateTime timestamp;
    private int status;
}
