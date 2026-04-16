package com.trung.notificationservice.controller;

import com.trung.notificationservice.dto.PageRequestDTO;
import com.trung.notificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @PostMapping("/send-mail")
    public ResponseEntity<String> sendMail(@ModelAttribute PageRequestDTO requestDTO) {
        notificationService.sendMailToAllUsers(requestDTO);
        return new ResponseEntity<>("Emails sent successfully", HttpStatus.OK);
    }
}
