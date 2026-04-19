package com.trung.orderservice.service.impl;

import com.trung.orderservice.dto.OrderCreateRequest;
import com.trung.orderservice.dto.OrderResponse;
import com.trung.orderservice.dto.ProductResponseDTO;
import com.trung.orderservice.entity.Orders;
import com.trung.orderservice.exception.InvalidDataException;
import com.trung.orderservice.exception.ResourceNotFoundException;
import com.trung.orderservice.exception.ServerErrorException;
import com.trung.orderservice.mapper.OrderMapper;
import com.trung.orderservice.repository.OrderRepository;
import com.trung.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductClient productClient;

    @Override
    @Transactional
    public OrderResponse createOrder(OrderCreateRequest request) throws InvalidDataException, ServerErrorException {

        try {
            ProductResponseDTO product = productClient.getProductById(request.getProductId());

            if (product.getStockQuantity() < request.getQuantity()) {
                throw new InvalidDataException("Insufficient stock for product id: " + request.getProductId());
            }

            productClient.reduceStock(request.getProductId(), request.getQuantity());

            Orders orders = new Orders();
            orders.setCustomerId(request.getCustomerId());
            orders.setProductId(request.getProductId());
            orders.setTotalAmount(product.getPrice() * request.getQuantity());

            orderRepository.save(orders);
            return OrderMapper.toDTO(orders);
        }catch (ServerErrorException e) {
            if (e.getMessage().contains("PRODUCT_SERVICE_ERROR")) {
                throw new ServerErrorException("Failed to fetch information from PRODUCT_SERVICE");
            }
            if (e.getMessage().contains("PRODUCT_SERVICE_UNAVAILABLE")) {
                throw new ServerErrorException("Product service is currently unavailable. Please try again later.");
            }
            throw e;
        }
    }

    @Override
    public OrderResponse getOrderById(Long id) throws ResourceNotFoundException {
        return orderRepository.findById(id)
                .map(OrderMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
    }
}
