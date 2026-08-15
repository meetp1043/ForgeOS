package com.forgeos.search.domain;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Structured search request model.
 */
public record SearchRequest(
        String query,
        List<String> resourceTypes,
        Map<String, Object> filters,
        String sort,
        int page,
        int pageSize,
        SearchMode searchMode,
        UUID projectId,
        Instant createdAfter,
        Instant createdBefore,
        List<String> facets
) {
    public SearchRequest {
        if (pageSize > 100) {
            pageSize = 100; // Apply hard maximum result limits
        }
    }
}
