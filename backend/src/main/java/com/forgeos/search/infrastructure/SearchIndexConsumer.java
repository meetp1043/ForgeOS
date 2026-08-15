package com.forgeos.search.infrastructure;

import com.forgeos.search.domain.SearchEngine;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

@Component
public class SearchIndexConsumer {

    private final SearchEngine searchEngine;

    public SearchIndexConsumer(SearchEngine searchEngine) {
        this.searchEngine = searchEngine;
    }

    // TODO: Create or import the actual event classes from the respective modules
    // Using Object as a placeholder for the generic domain event

    @ApplicationModuleListener
    public void onResourceCreatedEvent(Object event) {
        // 1. Validate tenant
        // 2. Validate resource
        // 3. Transform to SearchDocument
        // 4. Record success/failure (DLQ handled by Spring Modulith Event Publication Registry)
        // 5. searchEngine.index(document);
    }

    @ApplicationModuleListener
    public void onResourceUpdatedEvent(Object event) {
        // Convert and update
    }

    @ApplicationModuleListener
    public void onResourceDeletedEvent(Object event) {
        // extract documentId and call searchEngine.delete(documentId)
    }
}
