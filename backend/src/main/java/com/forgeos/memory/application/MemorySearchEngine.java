package com.forgeos.memory.application;

import com.forgeos.memory.domain.MemoryQuery;
import com.forgeos.memory.domain.MemorySearchResult;

import java.util.List;

public interface MemorySearchEngine {
    
    /**
     * Executes a hybrid search ensuring tenant and project isolation.
     * Incorporates keyword, metadata, and optional semantic vector search.
     */
    List<MemorySearchResult> search(MemoryQuery query);
}
