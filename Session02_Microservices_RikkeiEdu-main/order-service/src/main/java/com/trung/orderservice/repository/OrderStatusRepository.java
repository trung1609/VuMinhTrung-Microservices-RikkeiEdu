package com.trung.orderservice.repository;

import com.trung.orderservice.constant.OrderStatus;
import com.trung.orderservice.entity.OrderStatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatusHistory, Long> {
}
