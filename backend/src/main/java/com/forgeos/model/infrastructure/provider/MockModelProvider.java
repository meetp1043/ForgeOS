package com.forgeos.model.infrastructure.provider;

import com.forgeos.model.domain.ModelCapability;
import com.forgeos.model.domain.ModelPrivacyClassification;
import com.forgeos.model.domain.ModelRequest;
import com.forgeos.model.domain.ModelResponse;
import com.forgeos.model.domain.TokenUsage;
import com.forgeos.model.domain.ModelError;
import com.forgeos.model.domain.exception.ProviderException;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.UUID;

@Component
public class MockModelProvider implements ModelProvider {

    @Override
    public String getProviderName() {
        return "MOCK";
    }

    @Override
    public boolean isAvailable() {
        return true;
    }

    @Override
    public Set<ModelCapability> getSupportedCapabilities() {
        return Set.of(ModelCapability.CHAT, ModelCapability.STRUCTURED_OUTPUT);
    }

    @Override
    public ModelPrivacyClassification getMaxAllowedPrivacy() {
        return ModelPrivacyClassification.RESTRICTED; // Mock is safe for everything
    }

    @Override
    public ModelResponse execute(ModelRequest request) throws ProviderException {
        ModelResponse response = new ModelResponse();
        response.setContent("This is a mock response to: " + 
                (request.getUserMessages() != null && !request.getUserMessages().isEmpty() 
                        ? request.getUserMessages().get(0) 
                        : "empty request"));
        response.setProvider(getProviderName());
        response.setModel("mock-model");
        response.setFinishReason("STOP");
        response.setTokenUsage(new TokenUsage(10, 20, 0));
        response.setLatencyMs(5L);
        response.setRequestId(UUID.randomUUID().toString());
        
        return response;
    }
}
