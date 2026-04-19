package com.trung.notificationservice.service;

import com.trung.notificationservice.dto.PageRequestDTO;

public interface NotificationService {
    void sendMailToAllUsers(PageRequestDTO requestDTO);
}
