package com.example.metrics.kafka;

import com.example.metrics.dto.ApiMetricEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MetricsProducer {

    private static final String TOPIC = "api-metrics-topic";

    private final KafkaTemplate<String, ApiMetricEvent> kafkaTemplate;

    public void publishMetric(ApiMetricEvent event) {

        kafkaTemplate.send(TOPIC, event);
    }
}