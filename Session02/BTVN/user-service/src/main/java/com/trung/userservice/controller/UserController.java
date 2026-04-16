package com.trung.userservice.controller;

import com.trung.userservice.dto.PageRequestDTO;
import com.trung.userservice.dto.PageResponseDTO;
import com.trung.userservice.dto.UserResponse;
import com.trung.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/test")
    public String test() {
        return "Hello World! Welcome to User Service";
    }

    @GetMapping
    public ResponseEntity<PageResponseDTO<UserResponse>> getAllUsers(@ModelAttribute PageRequestDTO requestDTO) {
        return ResponseEntity.ok(userService.getAllUsers(requestDTO));
    }
}
