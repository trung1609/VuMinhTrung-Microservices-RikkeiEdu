package com.api.trackingservice.repository;

import com.api.trackingservice.entity.TrackingSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackingSummaryRepository extends JpaRepository<TrackingSummary, Long> {

}
