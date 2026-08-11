package com.forgeos.tools.domain.exception;

import com.forgeos.tools.domain.ToolErrorCode;

public class ToolValidationException extends ToolException {
    public ToolValidationException(String message) {
        super(ToolErrorCode.VALIDATION_FAILED, message);
    }
}
