package com.trung.userservice.controller;

import com.trung.userservice.dto.FormLogin;
import com.trung.userservice.dto.FormRegister;
import com.trung.userservice.dto.JwtResponse;
import com.trung.userservice.dto.UserResponse;
import com.trung.userservice.exception.InvalidCredentialsException;
import com.trung.userservice.exception.ResourceConflictException;
import com.trung.userservice.exception.ResourceNotFoundException;
import com.trung.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody FormRegister formRegister) throws ResourceConflictException, ResourceNotFoundException {
        return new ResponseEntity<>(userService.register(formRegister), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody FormLogin formLogin) throws InvalidCredentialsException {
        return new ResponseEntity<>(userService.login(formLogin), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }
}
