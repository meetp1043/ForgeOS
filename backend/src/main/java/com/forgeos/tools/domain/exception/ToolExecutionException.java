package com.forgeos.tools.domain.exception;

import com.forgeos.tools.domain.ToolErrorCode;

public class ToolExecutionException extends ToolException {
    public ToolExecutionException(ToolErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public ToolExecutionException(ToolErrorCode errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }
}
