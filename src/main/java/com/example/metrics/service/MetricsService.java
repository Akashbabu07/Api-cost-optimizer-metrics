package com.example.metrics.service;

import com.example.metrics.dto.ApiMetricEvent;

public interface MetricsService {

    void processMetric(ApiMetricEvent dto);
}