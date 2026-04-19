package com.api.trackingservice.dto;

import com.api.trackingservice.constant.TrackingStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrackingHistoryResponse {
    private TrackingStatus status;
    private LocalDateTime time;
}
