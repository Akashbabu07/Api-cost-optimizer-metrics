package com.example.metrics.service;

import com.example.metrics.entity.MetricEntity;
import java.util.List;

public interface MetricsQueryService {
    List<MetricEntity> getAllMetrics();
    void deleteAll();
}