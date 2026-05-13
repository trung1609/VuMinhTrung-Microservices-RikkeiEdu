package com.trung.shippingservice.service.impl;

import com.trung.shippingservice.event.OrderCreatedEvent;
import com.trung.shippingservice.constant.ShippingStatus;
import com.trung.shippingservice.dto.ShippingCreateRequest;
import com.trung.shippingservice.dto.ShippingCreateResponse;
import com.trung.shippingservice.dto.ShippingResponse;
import com.trung.shippingservice.entity.Shipment;
import com.trung.shippingservice.entity.ShippingItem;
import com.trung.shippingservice.exception.ResourceNotFoundException;
import com.trung.shippingservice.mapper.ShippingMapper;
import com.trung.shippingservice.repository.CarrierRepository;
import com.trung.shippingservice.repository.ShippingRepository;
import com.trung.shippingservice.service.ShippingService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShippingServiceImpl implements ShippingService {
    private final ShippingRepository shippingRepository;
    private final CarrierRepository carrierRepository;
    private final OrderClient orderClient;

    @Override
    @Transactional
    public ShippingCreateResponse createShipment(ShippingCreateRequest request) throws ResourceNotFoundException {
        Shipment shipment = new Shipment();

        if (orderClient.getOrderById(request.getOrderId()).getBody() == null) {
            throw new ResourceNotFoundException("Order not found with id: " + request.getOrderId());
        }

        shipment.setOrderId(request.getOrderId());
        shipment.setStatus(ShippingStatus.CREATED);
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
    @Transactional
    @KafkaListener(topics = "order-create", groupId = "shipping-group")
    public ShippingCreateResponse createShipment(OrderCreatedEvent event) throws ResourceNotFoundException {
        System.out.println(">>> Đã nhận được order từ Kafka: Order ID = " + event.getOrderId());

        Shipment shipment = new Shipment();

        // Kiểm tra order tồn tại
        if (orderClient.getOrderById(event.getOrderId()).getBody() == null) {
            throw new ResourceNotFoundException("Order not found with id: " + event.getOrderId());
        }

        shipment.setOrderId(event.getOrderId());
        shipment.setStatus(ShippingStatus.CREATED);

        shipment.setShippingFee(BigDecimal.valueOf(30000));

        // Convert OrderItemRequest items thành ShippingItem
        List<ShippingItem> items = event.getItems().stream().map(itemRequest -> {
            ShippingItem item = new ShippingItem();
            item.setProductId(itemRequest.getProductId());
            item.setQuantity(itemRequest.getQuantity());
            item.setShipmentId(shipment);
            return item;
        }).toList();
        shipment.setShippingItems(items);
        shippingRepository.save(shipment);

        System.out.println(">>> Đã lưu shipment vào DB với ID: " + shipment.getId());

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
