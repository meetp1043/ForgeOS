package com.forgeos.memory.application;

import com.forgeos.memory.domain.MemoryQuery;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class MemorySearchTests {

    @Test
    void testTenantIsolationEnforced() {
        MemorySearchEngine engine = new MemorySearchEngineImpl();
        
        // Query missing a Tenant ID
        MemoryQuery query = new MemoryQuery();
        query.setText("What database do we use?");
        
        assertThrows(SecurityException.class, () -> engine.search(query), 
            "Search engine must reject global unscoped queries.");
    }
}
