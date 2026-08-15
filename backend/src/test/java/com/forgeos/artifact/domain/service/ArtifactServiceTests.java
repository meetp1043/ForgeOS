package com.forgeos.artifact.domain.service;

import com.forgeos.artifact.domain.model.ArtifactStatus;
import com.forgeos.artifact.domain.model.ArtifactType;
import com.forgeos.artifact.infrastructure.persistence.ArtifactEntity;
import com.forgeos.artifact.infrastructure.persistence.ArtifactRepository;
import com.forgeos.organization.domain.security.TenantContextHolder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ArtifactServiceTests {

    private ArtifactRepository artifactRepository;
    private ObjectStorageProvider storageProvider;
    private ArtifactService artifactService;
    private UUID currentTenantId;

    @BeforeEach
    void setUp() {
        artifactRepository = mock(ArtifactRepository.class);
        storageProvider = mock(ObjectStorageProvider.class);
        artifactService = new ArtifactService(artifactRepository, storageProvider);

        currentTenantId = UUID.randomUUID();
        TenantContextHolder.setTenantId(currentTenantId);
    }

    @AfterEach
    void tearDown() {
        TenantContextHolder.clear();
    }

    @Test
    void testCreateAndUploadArtifact_Success() throws Exception {
        when(artifactRepository.save(any(ArtifactEntity.class))).thenAnswer(i -> {
            ArtifactEntity entity = i.getArgument(0);
            if (entity.getId() == null) {
                entity.setId(UUID.randomUUID());
            }
            return entity;
        });

        ArtifactEntity artifact = artifactService.createAndUploadArtifact(
                "test.txt", ArtifactType.DOCUMENT, "text/plain", 100L, new ByteArrayInputStream("hello".getBytes())
        );

        assertNotNull(artifact.getId());
        assertEquals(currentTenantId, artifact.getTenantId());
        assertEquals(ArtifactStatus.AVAILABLE, artifact.getStatus());
        assertEquals("test.txt", artifact.getName());
        assertTrue(artifact.getStorageKey().startsWith("tenants/" + currentTenantId));

        verify(storageProvider).putObject(eq(artifact.getStorageKey()), any());
    }

    @Test
    void testCreateAndUploadArtifact_Failure_CleansUp() throws Exception {
        when(artifactRepository.save(any(ArtifactEntity.class))).thenAnswer(i -> {
            ArtifactEntity entity = i.getArgument(0);
            if (entity.getId() == null) entity.setId(UUID.randomUUID());
            return entity;
        });

        doThrow(new RuntimeException("S3 is down")).when(storageProvider).putObject(any(), any());

        assertThrows(RuntimeException.class, () -> {
            artifactService.createAndUploadArtifact(
                    "test.txt", ArtifactType.DOCUMENT, "text/plain", 100L, new ByteArrayInputStream("hello".getBytes())
            );
        });

        // The entity status should technically be saved as FAILED, but the exception was thrown out.
        // We verify that cleanup was attempted.
        verify(storageProvider, atLeastOnce()).deleteObject(any());
    }

    @Test
    void testDownloadArtifact_CrossTenant_Blocked() {
        UUID artifactId = UUID.randomUUID();
        // Repository returns empty when querying by ID *and* currentTenantId
        when(artifactRepository.findByIdAndTenantId(artifactId, currentTenantId)).thenReturn(Optional.empty());

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            artifactService.downloadArtifact(artifactId);
        });

        assertEquals("Artifact not found or access denied", ex.getMessage());
    }
}
