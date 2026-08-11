package com.forgeos.context.application;

import com.forgeos.context.domain.ContextItem;
import com.forgeos.context.domain.ContextPack;
import com.forgeos.context.domain.ContextRequest;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContextBuilderTests {

    @Test
    void testBudgetTruncation() {
        ContextSecurityFilter filter = (items, req) -> items;
        
        ContextProvider mockProvider = new ContextProvider() {
            @Override
            public List<ContextItem> provideContext(ContextRequest request) {
                return List.of(
                    new ContextItem("1", "MEMORY", "HUMAN_APPROVED", 0.9, "Extremely important small fact."),
                    new ContextItem("2", "MEMORY", "MODEL_GENERATED", 0.5, "A".repeat(10000)) // Huge low-priority chunk
                );
            }

            @Override
            public boolean supports(ContextRequest request) { return true; }
        };
        
        ContextBuilder builder = new ContextBuilderImpl(Collections.singletonList(mockProvider), filter);
        
        ContextRequest request = new ContextRequest();
        request.setTenantId(UUID.randomUUID());
        // Set budget to ~100 tokens, which is smaller than the 10,000 char string (~2500 tokens)
        request.setContextBudgetTokens(100); 
        
        ContextPack pack = builder.build(request);
        
        // Assert the small item got in, but the huge one got truncated due to budget constraints
        assertTrue(pack.getEstimatedTokens() <= 100);
        boolean containsImportantFact = pack.getSections().values().stream()
                .flatMap(List::stream)
                .anyMatch(item -> item.getSourceId().equals("1"));
        assertTrue(containsImportantFact, "The high relevance, small budget item should be selected.");
    }
}
