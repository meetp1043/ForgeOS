package com.forgeos.model.application;

import com.forgeos.model.domain.ModelPolicy;
import com.forgeos.model.domain.ModelPrivacyClassification;
import com.forgeos.model.domain.ModelRequest;
import com.forgeos.model.domain.exception.ModelGatewayException;
import com.forgeos.model.infrastructure.provider.ModelProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ModelRouter {

    private final List<ModelProvider> providers;

    public ModelRouter(List<ModelProvider> providers) {
        this.providers = providers;
    }

    public List<ModelProvider> getProviderChain(ModelRequest request) {
        ModelPolicy policy = request.getPolicy() != null ? request.getPolicy() : ModelPolicy.DEFAULT;
        ModelPrivacyClassification privacy = request.getPrivacyClassification() != null 
                ? request.getPrivacyClassification() 
                : ModelPrivacyClassification.PUBLIC;

        List<ModelProvider> chain = new ArrayList<>();

        if (policy == ModelPolicy.MOCK_ONLY) {
            addIfAvailable(chain, "MOCK");
            return chain;
        }

        if (privacy == ModelPrivacyClassification.RESTRICTED || policy == ModelPolicy.LOCAL_ONLY) {
            addIfAvailable(chain, "OLLAMA");
            if (chain.isEmpty()) {
                throw new ModelGatewayException("No secure local provider available for RESTRICTED data");
            }
            return chain;
        }

        // Standard fallback chain: Try primary (OPENAI), fallback to local (OLLAMA), then MOCK
        addIfAvailable(chain, "OPENAI");
        addIfAvailable(chain, "OLLAMA");
        addIfAvailable(chain, "MOCK");

        if (chain.isEmpty()) {
            throw new ModelGatewayException("No providers available to handle the request");
        }

        return chain;
    }

    private void addIfAvailable(List<ModelProvider> chain, String providerName) {
        providers.stream()
                .filter(p -> p.getProviderName().equals(providerName))
                .findFirst()
                .ifPresent(p -> {
                    // Phase 24: Real check would verify QUARANTINED status and health endpoints
                    if (p.isAvailable()) {
                        chain.add(p);
                    }
                });
    }
}
