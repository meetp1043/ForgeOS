package com.forgeos.model.infrastructure.provider;

import com.forgeos.model.domain.ModelCapability;
import com.forgeos.model.domain.ModelPrivacyClassification;
import com.forgeos.model.domain.ModelRequest;
import com.forgeos.model.domain.ModelResponse;
import com.forgeos.model.domain.exception.ProviderException;

import java.util.Set;

public interface ModelProvider {
    
    String getProviderName();

    boolean isAvailable();

    Set<ModelCapability> getSupportedCapabilities();

    ModelPrivacyClassification getMaxAllowedPrivacy();

    ModelResponse execute(ModelRequest request) throws ProviderException;
}
