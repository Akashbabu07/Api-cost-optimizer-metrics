package com.example.metrics.mapper;

import com.example.metrics.dto.ApiMetricEvent;
import com.example.metrics.entity.MetricEntity;

public class MetricMapper {

    public static MetricEntity toEntity(ApiMetricEvent dto) {

        return new MetricEntity(
                dto.getApi(),
                dto.getMethod(),
                dto.getStatus(),
                dto.getLatency(),
                dto.getCost(),
                dto.getUserId(),
                dto.getTimestamp()
        );
    }
}