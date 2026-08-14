package com.forgeos.tenant.domain.service;

import com.forgeos.tenant.infrastructure.persistence.FeatureEntitlementEntity;
import com.forgeos.tenant.infrastructure.persistence.FeatureEntitlementRepository;
import com.forgeos.tenant.infrastructure.persistence.TenantEntity;
import com.forgeos.tenant.infrastructure.persistence.TenantRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class EntitlementService {

    private final TenantRepository tenantRepository;
    private final FeatureEntitlementRepository featureRepository;

    public EntitlementService(TenantRepository tenantRepository, FeatureEntitlementRepository featureRepository) {
        this.tenantRepository = tenantRepository;
        this.featureRepository = featureRepository;
    }

    public boolean isFeatureAllowed(UUID tenantId, String featureKey) {
        Optional<TenantEntity> tenantOpt = tenantRepository.findById(tenantId);
        if (tenantOpt.isEmpty()) {
            return false;
        }

        TenantEntity tenant = tenantOpt.get();
        if (!"ACTIVE".equals(tenant.getStatus()) && !"TRIAL".equals(tenant.getStatus())) {
            return false;
        }

        Optional<FeatureEntitlementEntity> entitlementOpt = featureRepository.findByTenantIdAndFeatureKey(tenantId, featureKey);
        return entitlementOpt.map(FeatureEntitlementEntity::isEnabled).orElse(false);
    }
}
