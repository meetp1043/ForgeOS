package com.forgeos.model.domain.exception;

public class ProviderRateLimitException extends ModelGatewayException {
    public ProviderRateLimitException(String providerName) {
        super("Rate limit exceeded for provider: " + providerName);
    }
}
