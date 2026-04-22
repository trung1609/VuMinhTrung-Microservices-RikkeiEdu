package com.trung.shippingservice.service.impl;

import com.trung.shippingservice.constant.ShippingStatus;
import com.trung.shippingservice.dto.ShippingCreateRequest;
import com.trung.shippingservice.dto.ShippingCreateResponse;
import com.trung.shippingservice.dto.ShippingResponse;
import com.trung.shippingservice.entity.Carrier;
import com.trung.shippingservice.entity.Shipment;
import com.trung.shippingservice.entity.ShippingItem;
import com.trung.shippingservice.exception.ResourceNotFoundException;
import com.trung.shippingservice.mapper.ShippingMapper;
import com.trung.shippingservice.repository.CarrierRepository;
import com.trung.shippingservice.repository.ShippingRepository;
import com.trung.shippingservice.service.ShippingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShippingServiceImpl implements ShippingService {
    private final ShippingRepository shippingRepository;
    private final CarrierRepository carrierRepository;

    @Override
    public ShippingCreateResponse createShipment(ShippingCreateRequest request) throws ResourceNotFoundException {
        Shipment shipment = new Shipment();
        shipment.setOrderId(request.getOrderId());
        shipment.setStatus(ShippingStatus.CREATED);
        Carrier carrier = carrierRepository.findById(request.getCarrierId()).orElseThrow(
                () -> new ResourceNotFoundException("Carrier not found with id: " + request.getCarrierId())
        );
        shipment.setCarrier(carrier);

        shipment.setShippingFee(BigDecimal.valueOf(30000));

        List<ShippingItem> items = request.getItems().stream().map(itemRequest -> {
            ShippingItem item = new ShippingItem();
            item.setProductId(itemRequest.getProductId());
            item.setQuantity(itemRequest.getQuantity());
            item.setShipmentId(shipment);
            return item;
        }).toList();
        shipment.setShippingItems(items);
        shippingRepository.save(shipment);

        return ShippingCreateResponse.builder()
                .shipmentId(shipment.getId())
                .status(ShippingStatus.CREATED)
                .shippingFee(shipment.getShippingFee())
                .build();
    }

    @Override
    public ShippingResponse getShipment(Long shipmentId) throws ResourceNotFoundException {
        Shipment shipment = shippingRepository.findById(shipmentId).orElseThrow(
                () -> new ResourceNotFoundException("Shipment not found with id: " + shipmentId)
        );
        return ShippingMapper.toDTO(shipment);
    }
}
