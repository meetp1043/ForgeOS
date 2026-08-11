package com.forgeos.model.infrastructure.provider;

import com.forgeos.model.domain.ModelCapability;
import com.forgeos.model.domain.ModelPrivacyClassification;
import com.forgeos.model.domain.ModelRequest;
import com.forgeos.model.domain.ModelResponse;
import com.forgeos.model.domain.exception.ModelGatewayException;

import java.util.Set;

public interface ModelProvider {
    
    /**
     * @return the unique name of the provider (e.g., "OLLAMA", "OPENAI")
     */
    String getProviderName();

    /**
     * @return true if the provider is currently configured and responsive
     */
    boolean isAvailable();

    /**
     * @return the set of capabilities this provider supports
     */
    Set<ModelCapability> getSupportedCapabilities();

    /**
     * @return the maximum data classification this provider is permitted to process
     */
    ModelPrivacyClassification getMaxAllowedPrivacy();

    /**
     * Executes a model generation request.
     * @param request the request
     * @return the structured response
     * @throws ModelGatewayException if the request fails
     */
    ModelResponse execute(ModelRequest request) throws ModelGatewayException;
}
