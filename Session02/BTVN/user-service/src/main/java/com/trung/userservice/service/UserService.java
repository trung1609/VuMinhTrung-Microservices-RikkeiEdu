package com.trung.userservice.service;

import com.trung.userservice.dto.PageRequestDTO;
import com.trung.userservice.dto.PageResponseDTO;
import com.trung.userservice.dto.UserResponse;

public interface UserService {
    PageResponseDTO<UserResponse> getAllUsers(PageRequestDTO requestDTO);
}
