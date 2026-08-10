package com.forgeos.shared.exception;

public class ForgeOSException extends RuntimeException {
    public ForgeOSException(String message) {
        super(message);
    }

    public ForgeOSException(String message, Throwable cause) {
        super(message, cause);
    }
}
