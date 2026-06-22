package com.example.metrics.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MetricResponseDTO {

    private String api;
    private String method;
    private int status;
    private long latency;
    private double cost;
    private LocalDateTime timestamp;
}