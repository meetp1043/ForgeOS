package com.forgeos.model.application;

import com.forgeos.model.domain.ModelPolicy;
import com.forgeos.model.domain.ModelPrivacyClassification;
import com.forgeos.model.domain.exception.ModelGatewayException;
import com.forgeos.model.infrastructure.provider.ModelProvider;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ModelRouter {

    private final List<ModelProvider> providers;

    public ModelRouter(List<ModelProvider> providers) {
        this.providers = providers;
    }

    public ModelProvider selectProvider(ModelPolicy policy, ModelPrivacyClassification privacyClassification) {
        if (policy == ModelPolicy.MOCK_ONLY) {
            return findProvider("MOCK");
        }

        if (privacyClassification == ModelPrivacyClassification.RESTRICTED || policy == ModelPolicy.LOCAL_ONLY) {
            ModelProvider ollama = findProvider("OLLAMA");
            if (ollama != null && ollama.isAvailable()) {
                return ollama;
            }
            if (privacyClassification == ModelPrivacyClassification.RESTRICTED) {
                throw new ModelGatewayException("No secure local provider available for RESTRICTED data");
            }
        }

        ModelProvider openai = findProvider("OPENAI");
        if (openai != null && openai.isAvailable()) {
            return openai;
        }
        
        ModelProvider ollama = findProvider("OLLAMA");
        if (ollama != null && ollama.isAvailable()) {
            return ollama;
        }

        ModelProvider mock = findProvider("MOCK");
        if (mock != null && mock.isAvailable()) {
            return mock;
        }

        throw new ModelGatewayException("No providers available to handle the request");
    }

    private ModelProvider findProvider(String name) {
        return providers.stream()
                .filter(p -> p.getProviderName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
