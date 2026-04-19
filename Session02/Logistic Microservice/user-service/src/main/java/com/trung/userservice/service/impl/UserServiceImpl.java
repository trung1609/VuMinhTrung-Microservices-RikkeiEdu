package com.trung.userservice.service.impl;

import com.trung.userservice.constant.UserStatus;
import com.trung.userservice.dto.FormLogin;
import com.trung.userservice.dto.FormRegister;
import com.trung.userservice.dto.JwtResponse;
import com.trung.userservice.dto.UserResponse;
import com.trung.userservice.entity.Roles;
import com.trung.userservice.entity.Users;
import com.trung.userservice.entity.UserRoles;
import com.trung.userservice.exception.InvalidCredentialsException;
import com.trung.userservice.exception.ResourceConflictException;
import com.trung.userservice.exception.ResourceNotFoundException;
import com.trung.userservice.repository.RoleRepository;
import com.trung.userservice.repository.UserRepository;
import com.trung.userservice.security.jwt.JwtProvider;
import com.trung.userservice.security.principal.UserPrincipal;
import com.trung.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    @Value("${jwt.expiration}")
    private long expire;

    @Override
    public UserResponse register(FormRegister formRegister) throws ResourceConflictException, ResourceNotFoundException {
        if (userRepository.findByEmail(formRegister.getEmail()).isPresent()) {
            throw new ResourceConflictException("Email already exists");
        }

        Users users = new Users();
        users.setEmail(formRegister.getEmail());
        users.setPassword(passwordEncoder.encode(formRegister.getPassword()));
        users.setFullName(formRegister.getFullName());
        users.setPhone(formRegister.getPhone());

        List<UserRoles> userRoles = new ArrayList<>();

        List<String> roleNames = formRegister.getRoles();

        if (roleNames == null || roleNames.isEmpty()) {
            roleNames = new ArrayList<>();
            roleNames.add("ROLE_USER");
        }

        for (String roleName : roleNames) {
            String normalizedRoleName = roleName.toUpperCase();
            if (!normalizedRoleName.startsWith("ROLE_")) {
                normalizedRoleName = "ROLE_" + normalizedRoleName;
            }

            Optional<Roles> roleOptional = roleRepository.findByName(normalizedRoleName);

            if (roleOptional.isPresent()) {
                UserRoles userRole = new UserRoles();
                userRole.setUserId(users);
                userRole.setRoleId(roleOptional.get());
                userRoles.add(userRole);
            } else {
                throw new ResourceNotFoundException("Role '" + normalizedRoleName + "' not found");
            }
        }

        users.setUserRoles(userRoles);
        users.setStatus(UserStatus.ACTIVE);
        Users savedUser = userRepository.save(users);

        return UserResponse.builder()
                .id(savedUser.getId())
                .email(savedUser.getEmail())
                .fullName(savedUser.getFullName())
                .roles(savedUser.getUserRoles().stream()
                        .map(userRole -> userRole.getRoleId().getName())
                        .toList())
                .createdAt(savedUser.getCreatedAt())
                .build();
    }

    @Override
    public JwtResponse login(FormLogin formLogin) throws InvalidCredentialsException {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(formLogin.getEmail(), formLogin.getPassword())
            );

            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

            Date expiration = new Date(new Date().getTime() + expire);
            String accessToken = jwtProvider.generateToken(userPrincipal.getUser());

            return JwtResponse.builder()
                    .accessToken(accessToken)
                    .expiresIn(expiration.getTime())
                    .build();
        }catch (AuthenticationException e) {
            log.error("Authentication failed: {}", e.getMessage());
            throw new InvalidCredentialsException("Invalid username or password");
        }
    }

    @Override
    public UserResponse getUserById(Long id) throws ResourceNotFoundException {
        Users users = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return UserResponse.builder()
                .id(users.getId())
                .email(users.getEmail())
                .fullName(users.getFullName())
                .roles(users.getUserRoles().stream()
                        .map(userRole -> userRole.getRoleId().getName())
                        .toList())
                .createdAt(users.getCreatedAt())
                .build();
    }
}