package com.example.metrics.controller;

import com.example.metrics.dto.ApiMetricEvent;
import com.example.metrics.dto.ApiResponse;
import com.example.metrics.entity.MetricEntity;
import com.example.metrics.service.MetricsQueryService;
import com.example.metrics.service.MetricsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/metrics")
@RequiredArgsConstructor
public class MetricsController {

    private final MetricsQueryService queryService;
    private final MetricsService metricsService;

    @GetMapping("/all")
    public ApiResponse<List<MetricEntity>> getAllMetrics() {

        return ApiResponse.<List<MetricEntity>>builder()
                .success(true)
                .message("Fetched metrics successfully")
                .data(queryService.getAllMetrics())
                .build();
    }
    @PostMapping("/ingest")
    public ApiResponse<String> ingestMetric(
            @RequestBody ApiMetricEvent event) {

        metricsService.processMetric(event);

        return ApiResponse.<String>builder()
                .success(true)
                .message("Metric processed successfully")
                .data("SUCCESS")
                .build();
    }

    @DeleteMapping("/clear")
    public ApiResponse<String> clearAll() {
        queryService.deleteAll();
        return ApiResponse.<String>builder()
                .success(true)
                .message("All metrics cleared")
                .data("SUCCESS")
                .build();
    }
}