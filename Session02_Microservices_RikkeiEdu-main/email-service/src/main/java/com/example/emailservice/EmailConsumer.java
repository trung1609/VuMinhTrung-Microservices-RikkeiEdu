package com.example.emailservice;

import com.example.emailservice.event.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailConsumer {
    private final JavaMailSender mailSender;

    @KafkaListener(topics = "order-create", groupId = "order-create-groupId-v3")
    public void sendOrderConfirmation(OrderCreatedEvent event) {
        // Thêm dòng này để kiểm tra dữ liệu thực tế
        System.out.println(">>> Đã nhận được yêu cầu gửi mail cho: " + event.getUserEmail());
        System.out.println(">>> Order ID: " + event.getOrderId() + ", Tổng tiền: " + event.getTotalAmount());

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("trung8d2005@gmail.com");
            message.setTo(event.getUserEmail());
            message.setSubject("Order Confirmation - Order #" + event.getOrderId());
            message.setText("Thank you for your order!\n" +
                    "Order ID: " + event.getOrderId() + "\n" +
                    "Total Amount: " + event.getTotalAmount() + "\n" +
                    "We will process your order shortly.");
            mailSender.send(message);
            System.out.println(">>> Email đã gửi thành công!");
        } catch (Exception e) {
            System.err.println(">>> Lỗi gửi mail: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
