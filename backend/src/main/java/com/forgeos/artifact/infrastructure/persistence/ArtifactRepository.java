package com.forgeos.artifact.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ArtifactRepository extends JpaRepository<ArtifactEntity, UUID> {
    Optional<ArtifactEntity> findByIdAndTenantId(UUID id, UUID tenantId);
}
