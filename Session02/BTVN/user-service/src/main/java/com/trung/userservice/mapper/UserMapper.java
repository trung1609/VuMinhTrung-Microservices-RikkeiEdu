package com.trung.userservice.mapper;

import com.trung.userservice.dto.UserResponse;
import com.trung.userservice.entity.Users;

public class UserMapper {
    public static UserResponse toDTO(Users users){
        return UserResponse.builder()
                .email(users.getEmail())
                .phone(users.getPhone())
                .address(users.getAddress())
                .build();
    }
}
