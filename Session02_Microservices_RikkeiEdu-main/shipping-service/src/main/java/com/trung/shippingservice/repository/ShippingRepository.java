package com.trung.shippingservice.repository;

import com.trung.shippingservice.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingRepository extends JpaRepository<Shipment, Long> {

}
