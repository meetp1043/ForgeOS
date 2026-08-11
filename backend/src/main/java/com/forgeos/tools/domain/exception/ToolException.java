package com.forgeos.tools.domain.exception;

import com.forgeos.tools.domain.ToolErrorCode;

public class ToolException extends RuntimeException {
    private final ToolErrorCode errorCode;

    public ToolException(ToolErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ToolException(ToolErrorCode errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public ToolErrorCode getErrorCode() {
        return errorCode;
    }
}
