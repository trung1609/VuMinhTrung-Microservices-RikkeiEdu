package com.trung.userservice.service.impl;

import com.trung.userservice.dto.PageRequestDTO;
import com.trung.userservice.dto.PageResponseDTO;
import com.trung.userservice.dto.UserResponse;
import com.trung.userservice.repository.UserRepository;
import com.trung.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public PageResponseDTO<UserResponse> getAllUsers(PageRequestDTO requestDTO) {
        Sort sort;
        if (requestDTO.getSize() == null || requestDTO.getSize() <= 0) {
            requestDTO.setSize(10);
        }

        if (requestDTO.getPage() == null || requestDTO.getPage() < 0) {
            requestDTO.setPage(0);
        }

        if (requestDTO.getSort() == null || requestDTO.getSort().equals("asc")) {
            sort = Sort.by(Sort.Direction.ASC, "id");
        } else {
            sort = Sort.by(Sort.Direction.DESC, "id");
        }

        Pageable pageable = PageRequest.of(requestDTO.getPage(), requestDTO.getSize(), sort);

        Page<UserResponse> userPage = userRepository.findAll(pageable)
                .map(user -> UserResponse.builder()
                        .email(user.getEmail())
                        .phone(user.getPhone())
                        .address(user.getAddress())
                        .build());

        return PageResponseDTO.<UserResponse>builder()
                .data(userPage.getContent())
                .page(userPage.getNumber())
                .size(userPage.getSize())
                .totalElements(userPage.getTotalElements())
                .build();
    }
}
