package com.example.metrics.service.impl;

import com.example.metrics.entity.MetricEntity;
import com.example.metrics.repository.MetricRepository;
import com.example.metrics.service.MetricsQueryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetricsQueryServiceImpl implements MetricsQueryService {

    private final MetricRepository repository;

    public MetricsQueryServiceImpl(MetricRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<MetricEntity> getAllMetrics() {
        return repository.findAll();
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}