package com.forgeos.context.application;

import com.forgeos.context.domain.ContextItem;
import com.forgeos.context.domain.ContextPack;
import com.forgeos.context.domain.ContextRequest;
import com.forgeos.context.domain.ContextSection;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ContextBuilderImpl implements ContextBuilder {

    private final List<ContextProvider> providers;
    private final ContextSecurityFilter securityFilter;

    public ContextBuilderImpl(List<ContextProvider> providers, ContextSecurityFilter securityFilter) {
        this.providers = providers;
        this.securityFilter = securityFilter;
    }

    @Override
    public ContextPack build(ContextRequest request) {
        // 1. Collect
        List<ContextItem> rawItems = new ArrayList<>();
        for (ContextProvider provider : providers) {
            if (provider.supports(request)) {
                rawItems.addAll(provider.provideContext(request));
            }
        }

        // 2. Filter (Security Defense in Depth)
        List<ContextItem> secureItems = securityFilter.filter(rawItems, request);

        // 3. Rank & Budget (Simplified logic for now)
        secureItems.sort((a, b) -> Double.compare(b.getRelevanceScore(), a.getRelevanceScore()));
        
        List<ContextItem> budgetedItems = applyBudget(secureItems, request.getContextBudgetTokens());

        // 4. Assemble
        Map<ContextSection, List<ContextItem>> sections = assembleSections(budgetedItems);

        ContextPack pack = new ContextPack();
        pack.setContextId(UUID.randomUUID());
        pack.setTenantId(request.getTenantId());
        pack.setProjectId(request.getProjectId());
        pack.setCreatedAt(OffsetDateTime.now());
        pack.setSections(sections);
        pack.setEstimatedTokens(estimateTokens(budgetedItems)); // Mock implementation

        return pack;
    }

    private List<ContextItem> applyBudget(List<ContextItem> items, int budgetTokens) {
        List<ContextItem> budgeted = new ArrayList<>();
        int currentTokens = 0;
        
        for (ContextItem item : items) {
            int itemTokens = item.getContent().length() / 4; // Extremely rough estimate
            if (currentTokens + itemTokens <= budgetTokens) {
                budgeted.add(item);
                currentTokens += itemTokens;
            } else {
                // Break once we hit the limit - relies on ranking prioritizing the best items first
                break;
            }
        }
        return budgeted;
    }

    private Map<ContextSection, List<ContextItem>> assembleSections(List<ContextItem> items) {
        Map<ContextSection, List<ContextItem>> map = new HashMap<>();
        
        for (ContextItem item : items) {
            // Very simple mapping for the proof of concept
            ContextSection section = ContextSection.MEMORY; 
            if ("FILE".equals(item.getSourceType())) {
                section = ContextSection.RELEVANT_FILES;
            }
            
            map.computeIfAbsent(section, k -> new ArrayList<>()).add(item);
        }
        
        return map;
    }
    
    private int estimateTokens(List<ContextItem> items) {
        return items.stream().mapToInt(i -> i.getContent().length() / 4).sum();
    }
}
