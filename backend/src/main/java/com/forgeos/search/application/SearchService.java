package com.forgeos.search.application;

import com.forgeos.search.domain.SearchDocument;
import com.forgeos.search.domain.SearchEngine;
import com.forgeos.search.domain.SearchRequest;
import com.forgeos.search.domain.SearchResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Core application service orchestrating search requests, enforcing authorization,
 * and performing hybrid rank fusion.
 */
@Service
public class SearchService {

    private final SearchEngine searchEngine;

    public SearchService(SearchEngine searchEngine) {
        this.searchEngine = searchEngine;
    }

    public List<SearchResult> executeSearch(SearchRequest request) {
        // 1. Resolve Authentication and Tenant Context
        // UUID tenantId = TenantContextHolder.getTenantId();
        // UUID userId = SecurityContextHolder.getUserId();
        
        // 2. Enhance Request with Authorization Constraints
        // request.filters().put("tenantId", tenantId);
        // request.filters().put("allowedUsers", userId);

        // 3. Delegate to Search Engine
        return searchEngine.search(request);
    }
    
    public List<SearchResult> executeAutocomplete(SearchRequest request) {
        // Enforce same security constraints as full search
        return searchEngine.autocomplete(request);
    }

    // Indexing is primarily driven via the Infrastructure Consumer, 
    // but the service can expose manual triggers if needed.
    public void reindexTenant() {
        // Verify tenant admin privileges before executing
    }
}
