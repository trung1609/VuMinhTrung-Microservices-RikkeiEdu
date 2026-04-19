package com.api.trackingservice.controller;

import com.api.trackingservice.dto.TrackingUpdateRequest;
import com.api.trackingservice.dto.TrackingUpdateResponse;
import com.api.trackingservice.exception.InvalidDataException;
import com.api.trackingservice.exception.ResourceNotFoundException;
import com.api.trackingservice.service.TrackingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/trackings")
@RequiredArgsConstructor
public class TrackingController {
    private final TrackingService trackingService;

    @PostMapping("/update")
    public ResponseEntity<TrackingUpdateResponse> updateTracking(@RequestBody TrackingUpdateRequest request) throws InvalidDataException, ResourceNotFoundException {
        return new ResponseEntity<>(trackingService.updateTrackingStatus(request), HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Object> getTrackingById(@PathVariable Long orderId) throws ResourceNotFoundException {
        return new ResponseEntity<>(trackingService.getTrackingById(orderId), HttpStatus.OK);
    }
}
