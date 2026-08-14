package com.forgeos.tenant.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsageRecordRepository extends JpaRepository<UsageRecordEntity, UUID> {
    Optional<UsageRecordEntity> findByEventId(String eventId);

    @Query("SELECT SUM(u.quantity) FROM UsageRecordEntity u WHERE u.tenantId = :tenantId AND u.resourceType = :resourceType")
    Long sumUsageByTenantAndResourceType(@Param("tenantId") UUID tenantId, @Param("resourceType") String resourceType);
}
