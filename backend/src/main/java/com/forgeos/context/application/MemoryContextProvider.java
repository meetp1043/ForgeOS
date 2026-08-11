package com.forgeos.context.application;

import com.forgeos.context.domain.ContextItem;
import com.forgeos.context.domain.ContextRequest;
import com.forgeos.memory.application.MemorySearchEngine;
import com.forgeos.memory.domain.MemoryQuery;
import com.forgeos.memory.domain.MemorySearchResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemoryContextProvider implements ContextProvider {

    private final MemorySearchEngine memorySearchEngine;

    public MemoryContextProvider(MemorySearchEngine memorySearchEngine) {
        this.memorySearchEngine = memorySearchEngine;
    }

    @Override
    public List<ContextItem> provideContext(ContextRequest request) {
        MemoryQuery query = new MemoryQuery();
        query.setTenantId(request.getTenantId());
        query.setProjectId(request.getProjectId());
        query.setText(request.getQuery() != null ? request.getQuery() : request.getObjective());
        
        List<MemorySearchResult> results = memorySearchEngine.search(query);
        
        return results.stream().map(res -> new ContextItem(
            res.getMemory().getId().toString(),
            "MEMORY",
            res.getMemory().getAuthority().name(),
            res.getScore(),
            res.getMemory().getContent()
        )).collect(Collectors.toList());
    }

    @Override
    public boolean supports(ContextRequest request) {
        return true; // Memory is generally always supported
    }
}
