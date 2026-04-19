package com.trung.shippingservice.service;

import com.trung.shippingservice.dto.ShippingCreateRequest;
import com.trung.shippingservice.dto.ShippingCreateResponse;
import com.trung.shippingservice.dto.ShippingResponse;
import com.trung.shippingservice.exception.ResourceNotFoundException;

public interface ShippingService {
    ShippingCreateResponse createShipment(ShippingCreateRequest request) throws ResourceNotFoundException;
    ShippingResponse getShipment(Long shipmentId) throws ResourceNotFoundException;
}
