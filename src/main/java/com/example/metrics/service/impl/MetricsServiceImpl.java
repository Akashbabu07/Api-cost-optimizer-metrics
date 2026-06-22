package com.example.metrics.service.impl;

import com.example.metrics.dto.ApiMetricEvent;
import com.example.metrics.entity.MetricEntity;
import com.example.metrics.kafka.MetricsProducer;
import com.example.metrics.mapper.MetricMapper;
import com.example.metrics.repository.MetricRepository;
import com.example.metrics.service.MetricsService;
import org.springframework.stereotype.Service;

@Service
public class MetricsServiceImpl implements MetricsService {

    private final MetricRepository repository;
    private final MetricsProducer metricsProducer;

    public MetricsServiceImpl(
            MetricRepository repository,
            MetricsProducer metricsProducer) {

        this.repository = repository;
        this.metricsProducer = metricsProducer;
    }

    @Override
    public void processMetric(ApiMetricEvent dto) {

        if (dto == null || dto.getApi() == null) {
            return;
        }

        // Default timestamp to now if not provided
        if (dto.getTimestamp() == null) {
            dto.setTimestamp(java.time.LocalDateTime.now());
        }

        MetricEntity entity = MetricMapper.toEntity(dto);

        repository.save(entity);

        metricsProducer.publishMetric(dto);
    }
}