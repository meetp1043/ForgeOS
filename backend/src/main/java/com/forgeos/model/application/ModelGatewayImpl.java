package com.forgeos.model.application;

import com.forgeos.model.domain.ModelPolicy;
import com.forgeos.model.domain.ModelPrivacyClassification;
import com.forgeos.model.domain.ModelRequest;
import com.forgeos.model.domain.ModelResponse;
import com.forgeos.model.domain.exception.ModelGatewayException;
import com.forgeos.model.infrastructure.provider.ModelProvider;
import org.springframework.stereotype.Service;

@Service
public class ModelGatewayImpl implements ModelGateway {

    private final ModelRouter router;

    public ModelGatewayImpl(ModelRouter router) {
        this.router = router;
    }

    @Override
    public ModelResponse generate(ModelRequest request) {
        validateRequest(request);

        ModelPolicy policy = request.getPolicy() != null ? request.getPolicy() : ModelPolicy.DEFAULT;
        ModelPrivacyClassification privacy = request.getPrivacyClassification() != null 
                ? request.getPrivacyClassification() 
                : ModelPrivacyClassification.PUBLIC;

        ModelProvider provider = router.selectProvider(policy, privacy);
        
        long startTime = System.currentTimeMillis();
        ModelResponse response = provider.execute(request);
        response.setLatencyMs(System.currentTimeMillis() - startTime);

        // TODO: Publish audit and observability events

        return response;
    }

    private void validateRequest(ModelRequest request) {
        if (request.getUserMessages() == null || request.getUserMessages().isEmpty()) {
            throw new ModelGatewayException("Model request must contain at least one user message");
        }
    }
}
