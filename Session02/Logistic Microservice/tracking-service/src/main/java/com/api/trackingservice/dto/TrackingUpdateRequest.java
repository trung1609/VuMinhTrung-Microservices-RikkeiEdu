package com.api.trackingservice.dto;

import com.api.trackingservice.constant.TrackingStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrackingUpdateRequest {
    private Long orderId;
    private TrackingStatus status;
    private String location;
}
