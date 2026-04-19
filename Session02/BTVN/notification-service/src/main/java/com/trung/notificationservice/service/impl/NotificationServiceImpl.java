package com.trung.notificationservice.service.impl;

import com.trung.notificationservice.dto.PageRequestDTO;
import com.trung.notificationservice.dto.PageResponseDTO;
import com.trung.notificationservice.dto.UserResponse;
import com.trung.notificationservice.service.EmailService;
import com.trung.notificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final UserClient userClient;
    private final EmailService emailService;

    @Override
    public void sendMailToAllUsers(PageRequestDTO requestDTO) {
        PageResponseDTO<UserResponse> users = userClient.getAllUsers(requestDTO);

        for (UserResponse user : users.getData()) {
            emailService.sendEmail(
                    user.getEmail(),
                    "Notification",
                    "I Love You");
        }
    }
}
