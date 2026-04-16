package com.trung.notificationservice.service.impl;

import com.trung.notificationservice.dto.PageRequestDTO;
import com.trung.notificationservice.dto.PageResponseDTO;
import com.trung.notificationservice.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class UserClient {
    private final RestTemplate restTemplate;

    public PageResponseDTO<UserResponse> getAllUsers(PageRequestDTO requestDTO) {
        String url = "http://localhost:8082/api/v1/users?pageNumber="
                + requestDTO.getPage()
                + "&pageSize=" + requestDTO.getSize();
        ResponseEntity<PageResponseDTO<UserResponse>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<PageResponseDTO<UserResponse>>() {
                }
        );

        return response.getBody();
    }
}
