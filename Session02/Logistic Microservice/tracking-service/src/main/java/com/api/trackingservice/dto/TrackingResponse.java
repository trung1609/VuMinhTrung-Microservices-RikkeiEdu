package com.api.trackingservice.dto;

import com.api.trackingservice.constant.TrackingStatus;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrackingResponse {
    private Long orderId;
    private TrackingStatus currentStatus;
    private List<TrackingHistoryResponse> history;
}
