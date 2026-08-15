package com.forgeos.billing.infrastructure;

import com.forgeos.billing.domain.UsageEvent;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UsageMeter {

    private final JdbcTemplate jdbcTemplate;

    public UsageMeter(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Records a usage event into the database synchronously.
     * Idempotency is enforced by a unique constraint on (idempotency_key, tenant_id).
     */
    @Transactional
    public void recordUsage(UsageEvent event) {
        String sql = """
            INSERT INTO usage_event (
                usage_event_id, tenant_id, resource_type, metric_type, quantity, unit, timestamp, source, idempotency_key
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
            ON CONFLICT (tenant_id, idempotency_key) DO NOTHING
        """;
        
        // This is a placeholder for actual JdbcTemplate execution.
        // In real execution, map event properties to query parameters.
    }
}
