package com.trung.shippingservice.controller;

import com.trung.shippingservice.dto.ShippingCreateRequest;
import com.trung.shippingservice.dto.ShippingCreateResponse;
import com.trung.shippingservice.dto.ShippingResponse;
import com.trung.shippingservice.exception.ResourceNotFoundException;
import com.trung.shippingservice.service.ShippingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/shipments")
@RequiredArgsConstructor
public class ShippingController {
    private final ShippingService shippingService;

    @PostMapping
    public ResponseEntity<ShippingCreateResponse> createShipment(@RequestBody ShippingCreateRequest request) throws ResourceNotFoundException {
        return new ResponseEntity<>(shippingService.createShipment(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShippingResponse> getShipment(@PathVariable Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(shippingService.getShipment(id), HttpStatus.OK);
    }
}
