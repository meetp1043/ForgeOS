package com.forgeos.model.application;

import com.forgeos.model.domain.ModelRequest;
import com.forgeos.model.domain.ModelResponse;

public interface ModelGateway {
    
    /**
     * Entrypoint for all AI model generation in ForgeOS.
     * Routes the request to the appropriate provider based on tenant policy and privacy constraints.
     * 
     * @param request the provider-agnostic model request
     * @return the structured response
     */
    ModelResponse generate(ModelRequest request);
}
