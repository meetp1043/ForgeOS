package com.forgeos.tenant.domain.service;

import com.forgeos.organization.infrastructure.persistence.OrganizationEntity;
import com.forgeos.organization.infrastructure.persistence.OrganizationMembershipEntity;
import com.forgeos.organization.infrastructure.persistence.OrganizationMembershipRepository;
import com.forgeos.tenant.infrastructure.persistence.FeatureEntitlementEntity;
import com.forgeos.tenant.infrastructure.persistence.FeatureEntitlementRepository;
import com.forgeos.tenant.infrastructure.persistence.QuotaDefinitionEntity;
import com.forgeos.tenant.infrastructure.persistence.QuotaDefinitionRepository;
import com.forgeos.tenant.infrastructure.persistence.TenantEntity;
import com.forgeos.tenant.infrastructure.persistence.TenantRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class TenantProvisioningService {

    private final TenantRepository tenantRepository;
    private final OrganizationMembershipRepository membershipRepository;
    private final FeatureEntitlementRepository featureRepository;
    private final QuotaDefinitionRepository quotaRepository;
    private final ApplicationEventPublisher eventPublisher;

    public TenantProvisioningService(TenantRepository tenantRepository,
                                     OrganizationMembershipRepository membershipRepository,
                                     FeatureEntitlementRepository featureRepository,
                                     QuotaDefinitionRepository quotaRepository,
                                     ApplicationEventPublisher eventPublisher) {
        this.tenantRepository = tenantRepository;
        this.membershipRepository = membershipRepository;
        this.featureRepository = featureRepository;
        this.quotaRepository = quotaRepository;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public TenantEntity provisionTenant(String name, String slug, UUID adminUserId) {
        if (tenantRepository.findBySlug(slug).isPresent()) {
            throw new IllegalArgumentException("Tenant with slug " + slug + " already exists.");
        }

        TenantEntity tenant = new TenantEntity();
        tenant.setName(name);
        tenant.setSlug(slug);
        tenant.setStatus("PROVISIONING");
        tenant.setPlanId("FREE");
        tenant = tenantRepository.save(tenant);

        eventPublisher.publishEvent("tenant.provisioning.started:" + tenant.getId());

        try {
            // Provision initial features
            FeatureEntitlementEntity feature = new FeatureEntitlementEntity();
            feature.setTenantId(tenant.getId());
            feature.setFeatureKey("AI_MODEL_ACCESS");
            feature.setEnabled(true);
            featureRepository.save(feature);

            // Provision initial quotas
            QuotaDefinitionEntity tokenQuota = new QuotaDefinitionEntity();
            tokenQuota.setTenantId(tenant.getId());
            tokenQuota.setResourceType("TOKENS");
            tokenQuota.setSoftLimit(100000);
            tokenQuota.setHardLimit(200000);
            quotaRepository.save(tokenQuota);

            // Provision admin membership
            OrganizationMembershipEntity membership = new OrganizationMembershipEntity();
            membership.setOrganizationId(tenant.getId()); // Using Tenant ID as Org ID for simple SaaS default
            membership.setUserId(adminUserId);
            membership.setRoleId(UUID.randomUUID()); // In a real app this would resolve the TENANT_ADMIN role ID
            membershipRepository.save(membership);

            tenant.setStatus("ACTIVE");
            tenant = tenantRepository.save(tenant);

            eventPublisher.publishEvent("tenant.provisioning.completed:" + tenant.getId());
            eventPublisher.publishEvent("tenant.activated:" + tenant.getId());

        } catch (Exception e) {
            tenant.setStatus("SUSPENDED"); // Fallback for failed provisioning
            tenantRepository.save(tenant);
            eventPublisher.publishEvent("tenant.provisioning.failed:" + tenant.getId());
            throw new RuntimeException("Tenant provisioning failed", e);
        }

        return tenant;
    }
}
