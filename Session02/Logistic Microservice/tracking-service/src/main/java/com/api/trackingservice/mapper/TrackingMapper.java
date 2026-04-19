package com.api.trackingservice.mapper;

import com.api.trackingservice.constant.TrackingStatus;
import com.api.trackingservice.dto.TrackingHistoryResponse;
import com.api.trackingservice.dto.TrackingResponse;
import com.api.trackingservice.entity.Tracking;
import com.api.trackingservice.entity.TrackingSummary;

public class TrackingMapper {
    public static TrackingResponse toDTO(TrackingSummary tracking){
        return TrackingResponse.builder()
                .orderId(tracking.getOrderId())
                .currentStatus(tracking.getCurrentStatus())
                .history(tracking.getTrackings().stream()
                        .map(t -> TrackingHistoryResponse.builder()
                                .status(t.getStatus())
                                .time(t.getCreatedAt())
                                .build())
                        .toList())
                .build();
    }
}
