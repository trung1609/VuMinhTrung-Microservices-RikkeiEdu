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
public class TrackingSummary {

    @Id
    private Long orderId;

    @Enumerated(EnumType.STRING)
    private TrackingStatus currentStatus;

    private LocalDateTime lastUpdated;

    @OneToMany(mappedBy = "trackingSummary", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Tracking> trackings;
}
