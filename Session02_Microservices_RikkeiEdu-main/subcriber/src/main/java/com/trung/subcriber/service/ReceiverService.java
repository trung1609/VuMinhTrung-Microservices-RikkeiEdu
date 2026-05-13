package com.trung.subcriber.service;

import org.springframework.stereotype.Service;

@Service
public class ReceiverService {
    public void receiveMessage(String message) {
        System.out.println("Received message: " + message);
    }
}
