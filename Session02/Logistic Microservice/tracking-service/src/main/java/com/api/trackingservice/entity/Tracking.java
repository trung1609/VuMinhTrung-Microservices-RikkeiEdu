package com.api.trackingservice.entity;

import com.api.trackingservice.constant.TrackingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tracking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id", insertable = false, updatable = false)
    private Long orderId;

    @Enumerated(EnumType.STRING)
    private TrackingStatus status;

    private String description;
    private String location;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private TrackingSummary trackingSummary;
}
