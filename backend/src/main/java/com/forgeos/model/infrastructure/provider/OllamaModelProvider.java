package com.forgeos.model.infrastructure.provider;

import com.forgeos.model.domain.ModelCapability;
import com.forgeos.model.domain.ModelPrivacyClassification;
import com.forgeos.model.domain.ModelRequest;
import com.forgeos.model.domain.ModelResponse;
import com.forgeos.model.domain.TokenUsage;
import com.forgeos.model.domain.ModelError;
import com.forgeos.model.domain.exception.ProviderException;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.UUID;

@Component
@ConditionalOnProperty(name = "spring.ai.ollama.base-url")
public class OllamaModelProvider implements ModelProvider {

    private final ChatClient chatClient;

    public OllamaModelProvider(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @Override
    public String getProviderName() {
        return "OLLAMA";
    }

    @Override
    public boolean isAvailable() {
        return true;
    }

    @Override
    public Set<ModelCapability> getSupportedCapabilities() {
        return Set.of(ModelCapability.CHAT);
    }

    @Override
    public ModelPrivacyClassification getMaxAllowedPrivacy() {
        return ModelPrivacyClassification.RESTRICTED; // Local AI is safe for everything
    }

    @Override
    public ModelResponse execute(ModelRequest request) throws ProviderException {
        try {
            ChatClient.PromptSpec promptSpec = chatClient.prompt()
                    .user(String.join("\n", request.getUserMessages()));
            
            if (request.getSystemInstruction() != null) {
                promptSpec.system(request.getSystemInstruction());
            }

            ChatResponse chatResponse = promptSpec.call().chatResponse();

            ModelResponse response = new ModelResponse();
            response.setContent(chatResponse.getResult().getOutput().getContent());
            response.setProvider(getProviderName());
            response.setModel(chatResponse.getMetadata().getModel());
            response.setFinishReason(chatResponse.getResult().getMetadata().getFinishReason());
            
            if (chatResponse.getMetadata().getUsage() != null) {
                response.setTokenUsage(new TokenUsage(
                    chatResponse.getMetadata().getUsage().getPromptTokens().intValue(),
                    chatResponse.getMetadata().getUsage().getGenerationTokens().intValue(),
                    0 // cachedTokens not easily available in spring-ai Ollama default
                ));
            }

            response.setRequestId(UUID.randomUUID().toString());
            return response;

        } catch (Exception e) {
            // Treat spring-ai execution exceptions as transient/timeout/server error
            throw new ProviderException("Ollama provider failed: " + e.getMessage(), e, ModelError.SERVER_ERROR, true);
        }
    }
}
