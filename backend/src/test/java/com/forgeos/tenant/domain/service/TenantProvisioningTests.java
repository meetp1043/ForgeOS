package com.forgeos.tenant.domain.service;

import com.forgeos.organization.infrastructure.persistence.OrganizationMembershipRepository;
import com.forgeos.tenant.infrastructure.persistence.FeatureEntitlementRepository;
import com.forgeos.tenant.infrastructure.persistence.QuotaDefinitionRepository;
import com.forgeos.tenant.infrastructure.persistence.TenantEntity;
import com.forgeos.tenant.infrastructure.persistence.TenantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationEventPublisher;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TenantProvisioningTests {

    private TenantProvisioningService provisioningService;
    private TenantRepository tenantRepository;
    private OrganizationMembershipRepository membershipRepository;
    private FeatureEntitlementRepository featureRepository;
    private QuotaDefinitionRepository quotaRepository;
    private ApplicationEventPublisher eventPublisher;

    @BeforeEach
    void setUp() {
        tenantRepository = mock(TenantRepository.class);
        membershipRepository = mock(OrganizationMembershipRepository.class);
        featureRepository = mock(FeatureEntitlementRepository.class);
        quotaRepository = mock(QuotaDefinitionRepository.class);
        eventPublisher = mock(ApplicationEventPublisher.class);

        provisioningService = new TenantProvisioningService(
                tenantRepository, membershipRepository, featureRepository, quotaRepository, eventPublisher
        );
    }

    @Test
    void testTenantProvisioningWorkflow() {
        when(tenantRepository.findBySlug("acme-corp")).thenReturn(Optional.empty());
        when(tenantRepository.save(any(TenantEntity.class))).thenAnswer(invocation -> {
            TenantEntity t = invocation.getArgument(0);
            if (t.getId() == null) {
                t.setId(UUID.randomUUID());
            }
            return t;
        });

        TenantEntity tenant = provisioningService.provisionTenant("Acme Corp", "acme-corp", UUID.randomUUID());

        assertNotNull(tenant.getId());
        assertEquals("ACTIVE", tenant.getStatus());
        assertEquals("FREE", tenant.getPlanId());

        verify(featureRepository, times(1)).save(any());
        verify(quotaRepository, times(1)).save(any());
        verify(membershipRepository, times(1)).save(any());

        verify(eventPublisher).publishEvent(startsWith("tenant.provisioning.started:"));
        verify(eventPublisher).publishEvent(startsWith("tenant.provisioning.completed:"));
        verify(eventPublisher).publishEvent(startsWith("tenant.activated:"));
    }

    @Test
    void testProvisioningIdempotency() {
        when(tenantRepository.findBySlug("acme-corp")).thenReturn(Optional.of(new TenantEntity()));

        assertThrows(IllegalArgumentException.class, () -> {
            provisioningService.provisionTenant("Acme Corp", "acme-corp", UUID.randomUUID());
        });
    }
}
