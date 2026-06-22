package com.example.metrics.repository;

import com.example.metrics.entity.MetricEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetricRepository extends JpaRepository<MetricEntity, Long> {
}