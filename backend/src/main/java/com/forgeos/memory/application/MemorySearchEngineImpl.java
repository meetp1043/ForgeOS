package com.forgeos.memory.application;

import com.forgeos.memory.domain.MemoryQuery;
import com.forgeos.memory.domain.MemorySearchResult;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class MemorySearchEngineImpl implements MemorySearchEngine {

    @Override
    public List<MemorySearchResult> search(MemoryQuery query) {
        if (query.getTenantId() == null) {
            throw new SecurityException("Tenant isolation failure. Cannot execute un-scoped global memory search.");
        }
        
        // In a real implementation:
        // 1. If EmbeddingProvider is available, fetch vector for query.getText()
        // 2. Query PostgreSQL combining full-text search (TSVECTOR) + pgvector similarity
        // 3. Filter strictly WHERE tenant_id = query.getTenantId()
        
        return Collections.emptyList();
    }
}
