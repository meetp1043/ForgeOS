package com.forgeos.observability.domain;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObservabilityConfiguration {

    @Bean
    public MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
        return registry -> registry.config()
                .commonTag("application", "forgeos-backend")
                .commonTag("environment", System.getenv().getOrDefault("SPRING_PROFILES_ACTIVE", "default"));
    }
}
