package com.example.metrics.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Entity
@Table(name = "api_metrics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MetricEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String api;
    private String method;
    private int status;
    private long latency;
    private double cost;
    private String userId;
    private LocalDateTime timestamp;

    public MetricEntity(String api, String method, int status,
                        long latency, double cost,
                        String userId, LocalDateTime timestamp) {

        this.api = api;
        this.method = method;
        this.status = status;
        this.latency = latency;
        this.cost = cost;
        this.userId = userId;
        this.timestamp = timestamp;
    }
}