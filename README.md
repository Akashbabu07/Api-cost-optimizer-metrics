# Metrics Service

> **The ingestion edge for API usage data — receives metric events from authenticated clients and publishes them to Kafka for async downstream processing.**

---

## Overview

The Metrics Service runs on port `8082` and is the write path for the entire platform. When an API call happens in a client system, that system sends a metric event here. The service persists it to its own PostgreSQL database and simultaneously publishes it as a Kafka message so the Analytics Service can consume and aggregate it asynchronously. This decoupled design means ingestion is always fast regardless of analytics load.

---

## Port

`8082`

---

## Responsibilities

- Accept incoming `ApiMetricEvent` payloads from JWT-authenticated clients
- Persist raw metric records to PostgreSQL (`metricsdb`)
- Publish metric events to Kafka topic for Analytics consumption
- Expose a listing endpoint to retrieve stored metrics

---

## API Endpoints

| Method | Endpoint | Auth Required | Description |
|--------|----------|---------------|-------------|
| POST | `/metrics/ingest` | Yes (JWT) | Ingest an API metric event |
| GET | `/metrics/all` | Yes (JWT) | List all stored metric records |

### Sample Ingest Payload

```json
{
  "apiId": "openai-gpt4",
  "provider": "OpenAI",
  "tokensUsed": 1500,
  "costUsd": 0.045,
  "responseTimeMs": 1200,
  "statusCode": 200,
  "timestamp": "2026-06-24T10:00:00Z"
}
```

---

## Database

- **Database:** `metricsdb` (PostgreSQL)
- **Schema managed by:** Hibernate DDL auto-update
- Stores raw metric event records

---

## Kafka

- **Role:** Producer
- **Serializer:** `JsonSerializer`
- Publishes `ApiMetricEvent` objects for the Analytics Service to consume

---

## Configuration

```yaml
server:
  port: 8082

spring:
  datasource:
    url: ${DB_URL:jdbc:postgresql://localhost:5432/metricsdb}
    username: ${DB_USERNAME:postgres}
    password: ${DB_PASSWORD}

  kafka:
    bootstrap-servers: ${KAFKA_BOOTSTRAP_SERVERS:localhost:9092}
```

---

## Environment Variables

| Variable | Description |
|----------|-------------|
| `DB_URL` | PostgreSQL connection URL (default: `jdbc:postgresql://localhost:5432/metricsdb`) |
| `DB_USERNAME` | Database username |
| `DB_PASSWORD` | Database password |
| `KAFKA_BOOTSTRAP_SERVERS` | Kafka broker address (default: `localhost:9092`) |
| `KAFKA_SECURITY_PROTOCOL` | Kafka security protocol (default: `PLAINTEXT`) |
| `KAFKA_SASL_MECHANISM` | SASL mechanism (for cloud Kafka) |
| `KAFKA_JAAS_CONFIG` | JAAS config string (for cloud Kafka auth) |

---

## Swagger UI

http://localhost:8082/swagger-ui.html

---

## Running

```bash
cd services/metrics
./mvnw spring-boot:run
```
