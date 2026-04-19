package com.trung.userservice.service.impl;

import com.trung.userservice.repository.UserRepository;
import com.trung.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
}
