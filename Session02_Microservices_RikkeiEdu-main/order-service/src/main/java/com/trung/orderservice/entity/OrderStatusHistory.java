package com.trung.orderservice.entity;

import com.trung.orderservice.constant.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderStatusHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Orders orderId;

    @Enumerated(EnumType.STRING)
    private com.trung.orderservice.constant.OrderStatusHistory status;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
