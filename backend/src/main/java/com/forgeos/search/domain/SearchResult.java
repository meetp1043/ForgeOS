package com.forgeos.search.domain;

import java.util.List;
import java.util.Map;

/**
 * Normalized search result.
 */
public record SearchResult(
        String resourceType,
        String resourceId,
        String title,
        String snippet,
        double score,
        Map<String, List<String>> highlights,
        Map<String, Object> metadata,
        String url
) {}
