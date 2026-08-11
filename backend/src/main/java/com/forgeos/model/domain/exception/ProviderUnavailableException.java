package com.forgeos.model.domain.exception;

public class ProviderUnavailableException extends ModelGatewayException {
    public ProviderUnavailableException(String providerName, String message) {
        super("Provider " + providerName + " is unavailable: " + message);
    }
}
