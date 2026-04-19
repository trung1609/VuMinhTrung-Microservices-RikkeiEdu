package com.trung.userservice.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreateRequest {
    private String email;
    private String phone;
    private String address;
}
