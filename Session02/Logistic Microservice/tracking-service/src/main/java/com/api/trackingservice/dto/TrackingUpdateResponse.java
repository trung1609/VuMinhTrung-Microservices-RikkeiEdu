package com.api.trackingservice.dto;

import com.api.trackingservice.constant.TrackingStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrackingUpdateResponse {
    private Long orderId;
    private TrackingStatus status;
}
