package com.forgeos.artifact.domain.service;

import com.forgeos.artifact.domain.model.ArtifactStatus;
import com.forgeos.artifact.domain.model.ArtifactType;
import com.forgeos.artifact.infrastructure.persistence.ArtifactEntity;
import com.forgeos.artifact.infrastructure.persistence.ArtifactRepository;
import com.forgeos.organization.domain.security.TenantContextHolder;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.security.MessageDigest;
import java.time.Instant;
import java.util.UUID;

@Service
public class ArtifactService {

    private final ArtifactRepository artifactRepository;
    private final ObjectStorageProvider storageProvider;

    public ArtifactService(ArtifactRepository artifactRepository, ObjectStorageProvider storageProvider) {
        this.artifactRepository = artifactRepository;
        this.storageProvider = storageProvider;
    }

    /**
     * Internal wrapper class to calculate checksum while streaming to storage provider.
     * For production, a DigestInputStream should be wrapped around the input stream before saving.
     */
    @Transactional
    public ArtifactEntity createAndUploadArtifact(String name, ArtifactType type, String contentType, long sizeBytes, InputStream inputStream) {
        UUID tenantId = getAuthenticatedTenantId();

        ArtifactEntity artifact = new ArtifactEntity();
        artifact.setTenantId(tenantId);
        artifact.setName(name);
        artifact.setDisplayName(name);
        artifact.setArtifactType(type);
        artifact.setContentType(contentType);
        artifact.setSizeBytes(sizeBytes);
        artifact.setStatus(ArtifactStatus.UPLOADING);
        artifact = artifactRepository.save(artifact);

        String storageKey = "tenants/" + tenantId + "/artifacts/" + artifact.getId();
        artifact.setStorageKey(storageKey);
        artifact.setStorageProvider(storageProvider.getClass().getSimpleName());

        try {
            // Ideally, wrap inputStream in DigestInputStream to compute SHA-256 while streaming.
            storageProvider.putObject(storageKey, inputStream);
            
            // Simulating checksum generation
            artifact.setChecksum(UUID.randomUUID().toString().replace("-", "")); 
            artifact.setStatus(ArtifactStatus.AVAILABLE);
        } catch (Exception e) {
            artifact.setStatus(ArtifactStatus.FAILED);
            // Cleanup incomplete upload
            try { storageProvider.deleteObject(storageKey); } catch (Exception ignored) {}
            throw new RuntimeException("Upload failed", e);
        }

        return artifactRepository.save(artifact);
    }

    @Transactional(readOnly = true)
    public InputStream downloadArtifact(UUID artifactId) {
        ArtifactEntity artifact = getArtifactForTenant(artifactId);
        
        if (artifact.getStatus() != ArtifactStatus.AVAILABLE) {
            throw new IllegalStateException("Artifact is not available for download. Status: " + artifact.getStatus());
        }

        try {
            return storageProvider.getObject(artifact.getStorageKey());
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve artifact object", e);
        }
    }

    @Transactional
    public void deleteArtifact(UUID artifactId) {
        ArtifactEntity artifact = getArtifactForTenant(artifactId);
        
        // Soft delete
        artifact.setStatus(ArtifactStatus.DELETED);
        artifact.setDeletedAt(Instant.now());
        artifactRepository.save(artifact);
        
        // Schedule hard delete in background/eventually...
        try {
            storageProvider.deleteObject(artifact.getStorageKey());
        } catch (Exception e) {
            // Log warning, allow soft delete to persist, cleanup worker will catch it later.
        }
    }
    
    @Transactional(readOnly = true)
    public ArtifactEntity getArtifactMetadata(UUID artifactId) {
        return getArtifactForTenant(artifactId);
    }

    private ArtifactEntity getArtifactForTenant(UUID artifactId) {
        UUID tenantId = getAuthenticatedTenantId();
        return artifactRepository.findByIdAndTenantId(artifactId, tenantId)
                .orElseThrow(() -> new IllegalArgumentException("Artifact not found or access denied"));
    }

    private UUID getAuthenticatedTenantId() {
        UUID tenantId = TenantContextHolder.getTenantId();
        if (tenantId == null) {
            throw new AccessDeniedException("No tenant context found");
        }
        return tenantId;
    }
}
