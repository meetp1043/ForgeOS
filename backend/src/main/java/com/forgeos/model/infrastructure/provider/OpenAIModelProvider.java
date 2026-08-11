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
@ConditionalOnProperty(name = "spring.ai.openai.api-key")
public class OpenAIModelProvider implements ModelProvider {

    private final ChatClient chatClient;

    public OpenAIModelProvider(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @Override
    public String getProviderName() {
        return "OPENAI";
    }

    @Override
    public boolean isAvailable() {
        return true;
    }

    @Override
    public Set<ModelCapability> getSupportedCapabilities() {
        return Set.of(
            ModelCapability.CHAT,
            ModelCapability.STRUCTURED_OUTPUT,
            ModelCapability.TOOL_CALLING
        );
    }

    @Override
    public ModelPrivacyClassification getMaxAllowedPrivacy() {
        return ModelPrivacyClassification.CONFIDENTIAL;
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
                    0 
                ));
            }

            response.setRequestId(UUID.randomUUID().toString());
            return response;

        } catch (Exception e) {
            String msg = e.getMessage().toLowerCase();
            ModelError errorType = ModelError.SERVER_ERROR;
            boolean retryable = true;

            if (msg.contains("rate limit") || msg.contains("429")) {
                errorType = ModelError.RATE_LIMIT;
            } else if (msg.contains("timeout") || msg.contains("deadline")) {
                errorType = ModelError.TIMEOUT;
            } else if (msg.contains("unauthorized") || msg.contains("401")) {
                errorType = ModelError.AUTHENTICATION_ERROR;
                retryable = false;
            } else if (msg.contains("400")) {
                errorType = ModelError.INVALID_REQUEST;
                retryable = false;
            }

            throw new ProviderException("OpenAI provider failed: " + e.getMessage(), e, errorType, retryable);
        }
    }
}
