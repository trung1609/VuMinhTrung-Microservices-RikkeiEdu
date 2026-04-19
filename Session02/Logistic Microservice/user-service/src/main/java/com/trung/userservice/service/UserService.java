package com.trung.userservice.service;

import com.trung.userservice.dto.FormLogin;
import com.trung.userservice.dto.FormRegister;
import com.trung.userservice.dto.JwtResponse;
import com.trung.userservice.dto.UserResponse;
import com.trung.userservice.exception.InvalidCredentialsException;
import com.trung.userservice.exception.ResourceConflictException;
import com.trung.userservice.exception.ResourceNotFoundException;

public interface UserService {
    UserResponse register(FormRegister formRegister) throws ResourceConflictException, ResourceNotFoundException;
    JwtResponse login(FormLogin formLogin) throws InvalidCredentialsException;
    UserResponse getUserById(Long id) throws ResourceNotFoundException;
}
