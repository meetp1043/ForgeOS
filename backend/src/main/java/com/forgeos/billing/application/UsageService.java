package com.forgeos.billing.application;

import com.forgeos.billing.domain.UsageEvent;
import com.forgeos.billing.infrastructure.UsageMeter;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UsageService {

    private final UsageMeter usageMeter;

    public UsageService(UsageMeter usageMeter) {
        this.usageMeter = usageMeter;
    }

    public void enforceQuota(UUID tenantId, String metricType, long quantityToConsume) {
        // 1. Fetch current aggregated usage for the billing period
        // 2. Fetch plan entitlements
        // 3. If (currentUsage + quantityToConsume) > limit:
        //    throw new QuotaExceededException("Quota exceeded for " + metricType);
    }

    public void recordUsage(UsageEvent event) {
        // 1. Validate the source
        // 2. Asynchronously save usage event
        usageMeter.recordUsage(event);
        
        // 3. Check for thresholds (e.g. 80%, 100%) and emit warnings via Event Bus
    }
}
