package com.forgeos.context.application;

import com.forgeos.context.domain.ContextItem;
import com.forgeos.context.domain.ContextRequest;

import java.util.List;

public interface ContextProvider {
    /**
     * Retrieves relevant context based on the request.
     * Providers are NOT responsible for global budget management or final security filtering,
     * but they SHOULD attempt to only return relevant information.
     */
    List<ContextItem> provideContext(ContextRequest request);
    
    /**
     * Determines whether this provider is relevant for the requested agent role/task.
     */
    boolean supports(ContextRequest request);
}
