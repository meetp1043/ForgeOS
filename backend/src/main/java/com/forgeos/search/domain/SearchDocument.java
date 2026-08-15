package com.forgeos.search.domain;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

/**
 * Generic searchable resource representation.
 * Every indexed resource must have a stable identity: resourceType + resourceId + tenantId.
 */
public record SearchDocument(
        String documentId,
        UUID tenantId,
        UUID organizationId,
        UUID projectId,
        String resourceType,
        String resourceId,
        String title,
        String description,
        String content,
        Map<String, Object> metadata,
        Map<String, Object> permissions,
        Instant createdAt,
        Instant updatedAt,
        Instant deletedAt,
        Long indexVersion,
        float[] embedding
) {
    public static String generateDocumentId(String resourceType, String resourceId, UUID tenantId) {
        return String.format("%s:%s:%s", tenantId, resourceType, resourceId);
    }
}
