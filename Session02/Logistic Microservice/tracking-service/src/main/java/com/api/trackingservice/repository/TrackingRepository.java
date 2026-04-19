package com.api.trackingservice.repository;

import com.api.trackingservice.entity.Tracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface TrackingRepository extends JpaRepository<Tracking, Long> {
    Optional<Tracking> findByOrderId(Long orderId);
}
