package com.forgeos.model.domain.exception;

import com.forgeos.model.domain.ModelError;

public class ProviderException extends RuntimeException {
    
    private final ModelError errorType;
    private final boolean retryable;

    public ProviderException(String message, ModelError errorType, boolean retryable) {
        super(message);
        this.errorType = errorType;
        this.retryable = retryable;
    }

    public ProviderException(String message, Throwable cause, ModelError errorType, boolean retryable) {
        super(message, cause);
        this.errorType = errorType;
        this.retryable = retryable;
    }

    public ModelError getErrorType() { return errorType; }
    public boolean isRetryable() { return retryable; }
}
