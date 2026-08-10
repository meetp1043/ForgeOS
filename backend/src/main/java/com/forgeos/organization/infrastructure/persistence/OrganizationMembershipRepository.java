package com.forgeos.organization.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrganizationMembershipRepository extends JpaRepository<OrganizationMembershipEntity, UUID> {
    Optional<OrganizationMembershipEntity> findByOrganizationIdAndUserId(UUID organizationId, UUID userId);
}
