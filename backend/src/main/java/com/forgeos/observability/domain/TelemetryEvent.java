package com.forgeos.observability.domain;

import java.time.Instant;

public record TelemetryEvent(String traceId, String spanId, String name, Instant timestamp) {}
