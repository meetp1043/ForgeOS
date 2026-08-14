package com.forgeos.tenant.domain.service;

import com.forgeos.tenant.infrastructure.persistence.QuotaDefinitionEntity;
import com.forgeos.tenant.infrastructure.persistence.QuotaDefinitionRepository;
import com.forgeos.tenant.infrastructure.persistence.UsageRecordEntity;
import com.forgeos.tenant.infrastructure.persistence.UsageRecordRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class UsageMeteringService {

    private final UsageRecordRepository usageRecordRepository;
    private final QuotaDefinitionRepository quotaDefinitionRepository;
    private final ApplicationEventPublisher eventPublisher;

    public UsageMeteringService(UsageRecordRepository usageRecordRepository,
                                QuotaDefinitionRepository quotaDefinitionRepository,
                                ApplicationEventPublisher eventPublisher) {
        this.usageRecordRepository = usageRecordRepository;
        this.quotaDefinitionRepository = quotaDefinitionRepository;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public void recordUsage(String eventId, UUID tenantId, String resourceType, long quantity) {
        if (usageRecordRepository.findByEventId(eventId).isPresent()) {
            // Idempotency check: already processed this event
            return;
        }

        UsageRecordEntity usage = new UsageRecordEntity();
        usage.setEventId(eventId);
        usage.setTenantId(tenantId);
        usage.setResourceType(resourceType);
        usage.setQuantity(quantity);
        usageRecordRepository.save(usage);

        checkQuotas(tenantId, resourceType);
    }

    public boolean isHardQuotaExceeded(UUID tenantId, String resourceType) {
        Long currentUsage = usageRecordRepository.sumUsageByTenantAndResourceType(tenantId, resourceType);
        if (currentUsage == null) currentUsage = 0L;

        List<QuotaDefinitionEntity> quotas = quotaDefinitionRepository.findByTenantId(tenantId);
        for (QuotaDefinitionEntity quota : quotas) {
            if (quota.getResourceType().equals(resourceType)) {
                return currentUsage >= quota.getHardLimit();
            }
        }

        return false;
    }

    private void checkQuotas(UUID tenantId, String resourceType) {
        Long currentUsage = usageRecordRepository.sumUsageByTenantAndResourceType(tenantId, resourceType);
        if (currentUsage == null) return;

        List<QuotaDefinitionEntity> quotas = quotaDefinitionRepository.findByTenantId(tenantId);
        for (QuotaDefinitionEntity quota : quotas) {
            if (quota.getResourceType().equals(resourceType)) {
                if (currentUsage >= quota.getHardLimit()) {
                    eventPublisher.publishEvent("tenant.quota.exceeded.hard:" + tenantId + ":" + resourceType);
                } else if (currentUsage >= quota.getSoftLimit()) {
                    eventPublisher.publishEvent("tenant.quota.exceeded.soft:" + tenantId + ":" + resourceType);
                }
            }
        }
    }
}
