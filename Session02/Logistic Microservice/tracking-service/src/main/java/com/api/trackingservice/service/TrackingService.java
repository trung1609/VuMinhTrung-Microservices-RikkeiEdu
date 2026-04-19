package com.api.trackingservice.service;

import com.api.trackingservice.dto.TrackingResponse;
import com.api.trackingservice.dto.TrackingUpdateRequest;
import com.api.trackingservice.dto.TrackingUpdateResponse;
import com.api.trackingservice.exception.InvalidDataException;
import com.api.trackingservice.exception.ResourceNotFoundException;

public interface TrackingService {
    TrackingResponse getTrackingById(Long orderId) throws ResourceNotFoundException;
    TrackingUpdateResponse updateTrackingStatus(TrackingUpdateRequest request) throws ResourceNotFoundException, InvalidDataException;
}
