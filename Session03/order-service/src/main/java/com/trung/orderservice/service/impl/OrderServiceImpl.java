package com.trung.orderservice.service.impl;

import com.trung.orderservice.dto.OrderCreateRequest;
import com.trung.orderservice.dto.OrderResponse;
import com.trung.orderservice.entity.Orders;
import com.trung.orderservice.exception.ResourceNotFoundException;
import com.trung.orderservice.mapper.OrderMapper;
import com.trung.orderservice.repository.OrderRepository;
import com.trung.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;


    @Override
    public OrderResponse createOrder(OrderCreateRequest request) {
        Orders orders = new Orders();

        orders.setCustomerId(request.getCustomerId());
        orders.setProductId(request.getProductId());
        orders.setTotalAmount(request.getQuantity() * request.getPriceProduct());

        orderRepository.save(orders);
        return OrderMapper.toDTO(orders);
    }

    @Override
    public OrderResponse getOrderById(Long id) throws ResourceNotFoundException {
        return orderRepository.findById(id)
                .map(OrderMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
    }
}
