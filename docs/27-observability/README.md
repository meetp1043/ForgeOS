# Observability & Reliability Platform (Phase 27)

## Overview
ForgeOS utilizes an OpenTelemetry and Micrometer-backed observability platform designed for extreme reliability. The telemetry pipeline connects API requests to multi-agent workflows across strict tenant boundaries without leaking high-cardinality PII or secrets.

## Features
1. **Distributed Tracing (OpenTelemetry)**:
   - Traces are propagated implicitly via the OpenTelemetry Java Agent (when running in production) or via Micrometer bridge locally. 
   - Operations output a `traceId` which is exposed even to clients during errors, accelerating root-cause analysis (RCA).
2. **Structured Logging (JSON)**:
   - Powered by `logstash-logback-encoder`.
   - Native secret redaction masks fields like `password`, `token`, and `API-Key` using RegEx (`MaskingJsonGeneratorDecorator`).
   - `MdcContextFilter` automatically captures `X-Request-ID` and the active `tenantId`, stamping every single log with cross-cutting context.
3. **Normalized Error Handling**:
   - `GlobalExceptionHandler` intercepts all Spring MVC exceptions.
   - It swallows the stack trace (preventing it from leaking) and responds with `NormalizedErrorResponse` containing a generated `errorId`, the `traceId`, and a generalized `errorCode` (e.g. `VALIDATION_ERROR`, `INTERNAL_ERROR`).
4. **Metrics & Health (Actuator & Prometheus)**:
   - `/actuator/prometheus` provides real-time access to JVM telemetry, HTTP connection pools, and database active connection metrics.
   - Separate `/actuator/health/liveness` and `readiness` endpoints allow precise Kubernetes orchestrator checks.

## Dashboards & Runbooks (Conceptual)
With Prometheus collecting metrics and OTEL distributing traces, operators can visualize:
- **System Dashboard**: RPS, 4xx/5xx counts, P95/P99 latencies.
- **Agent Dashboard**: Success/Failure rate of tool executions grouped by Tenant.
- **Outage Runbook**: If `API availability` drops below `99.9%` (SLO breach), operators use the `traceId` reported in `NormalizedErrorResponse` to jump directly into Jaeger/Grafana Tempo to isolate the failing span (e.g., Model Gateway timeout vs Event Bus latency).
