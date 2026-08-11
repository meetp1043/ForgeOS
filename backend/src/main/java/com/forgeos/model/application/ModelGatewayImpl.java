package com.forgeos.model.application;

import com.forgeos.model.domain.ModelCost;
import com.forgeos.model.domain.ModelRequest;
import com.forgeos.model.domain.ModelResponse;
import com.forgeos.model.domain.exception.ModelGatewayException;
import com.forgeos.model.domain.exception.ProviderException;
import com.forgeos.model.infrastructure.provider.ModelProvider;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelGatewayImpl implements ModelGateway {

    private final ModelRouter router;

    public ModelGatewayImpl(ModelRouter router) {
        this.router = router;
    }

    @Override
    public ModelResponse generate(ModelRequest request) {
        validateRequest(request);

        List<ModelProvider> providerChain = router.getProviderChain(request);
        
        Throwable lastError = null;
        boolean fallbackUsed = false;

        for (ModelProvider provider : providerChain) {
            try {
                long startTime = System.currentTimeMillis();
                ModelResponse response = provider.execute(request);
                response.setLatencyMs(System.currentTimeMillis() - startTime);
                response.setFallbackUsed(fallbackUsed);
                
                // Enforce Budget
                validateBudget(request, response);

                return response;
            } catch (ProviderException e) {
                lastError = e;
                if (!e.isRetryable()) {
                    throw new ModelGatewayException("Non-retryable provider error: " + e.getMessage(), e);
                }
                fallbackUsed = true;
                // Continue to the next provider in the chain
            }
        }

        throw new ModelGatewayException("All providers in the routing chain failed. Last error: " + 
                (lastError != null ? lastError.getMessage() : "Unknown"), lastError);
    }

    private void validateRequest(ModelRequest request) {
        if (request.getUserMessages() == null || request.getUserMessages().isEmpty()) {
            throw new ModelGatewayException("Model request must contain at least one user message");
        }
    }

    private void validateBudget(ModelRequest request, ModelResponse response) {
        if (request.getBudget() != null && response.getTokenUsage() != null) {
            // Primitive cost calculation for testing
            double estimatedCost = response.getTokenUsage().getTotalTokens() * 0.0001; 
            if (estimatedCost > request.getBudget()) {
                throw new ModelGatewayException("Budget Exceeded! Cost: " + estimatedCost + " > Budget: " + request.getBudget());
            }
        }
    }
}
