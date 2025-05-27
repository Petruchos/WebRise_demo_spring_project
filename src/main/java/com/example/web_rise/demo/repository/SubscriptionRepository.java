package com.example.web_rise.demo.repository;

import com.example.web_rise.demo.dto.SubscriptionStatistics;
import com.example.web_rise.demo.entity.SubscriptionEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, Long> {
    @Query("SELECT new com.example.web_rise.demo.dto.SubscriptionStatistics(s.subscriptionType, COUNT(s)) " +
            "FROM SubscriptionEntity s " +
            "GROUP BY s.subscriptionType " +
            "ORDER BY COUNT(s) DESC")
    List<SubscriptionStatistics> findTop3SubscriptionTypesDto(Pageable pageable);
}
