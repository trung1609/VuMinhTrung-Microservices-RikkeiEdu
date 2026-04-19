package com.api.trackingservice.service.impl;

import com.api.trackingservice.dto.TrackingResponse;
import com.api.trackingservice.dto.TrackingUpdateRequest;
import com.api.trackingservice.dto.TrackingUpdateResponse;
import com.api.trackingservice.entity.Tracking;
import com.api.trackingservice.entity.TrackingSummary;
import com.api.trackingservice.exception.InvalidDataException;
import com.api.trackingservice.exception.ResourceNotFoundException;
import com.api.trackingservice.mapper.TrackingMapper;
import com.api.trackingservice.repository.TrackingRepository;
import com.api.trackingservice.repository.TrackingSummaryRepository;
import com.api.trackingservice.service.TrackingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TrackingServiceImpl implements TrackingService {
    private final TrackingRepository trackingRepository;
    private final TrackingSummaryRepository trackingSummaryRepository;


    @Override
    public TrackingResponse getTrackingById(Long orderId) throws ResourceNotFoundException {
        TrackingSummary trackingSummary = trackingSummaryRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Tracking summary not found for orderId: " + orderId));

        return TrackingMapper.toDTO(trackingSummary);
    }

    @Override
    @Transactional
    public TrackingUpdateResponse updateTrackingStatus(TrackingUpdateRequest request) throws ResourceNotFoundException, InvalidDataException {
        TrackingSummary trackingSummary = trackingSummaryRepository.findById(request.getOrderId())
                .orElseGet(() -> {
                    TrackingSummary newSummary = new TrackingSummary();
                    newSummary.setOrderId(request.getOrderId());
                    return newSummary;
                });

        trackingSummary.setCurrentStatus(request.getStatus());
        trackingSummary.setLastUpdated(LocalDateTime.now());
        trackingSummaryRepository.save(trackingSummary);

        Tracking tracking = new Tracking();
        tracking.setStatus(request.getStatus());
        tracking.setTrackingSummary(trackingSummary);
        tracking.setCreatedAt(LocalDateTime.now());
        tracking.setLocation(request.getLocation());
        trackingRepository.save(tracking);

        return TrackingUpdateResponse.builder()
                .orderId(request.getOrderId())
                .status(request.getStatus())
                .build();
    }
}
