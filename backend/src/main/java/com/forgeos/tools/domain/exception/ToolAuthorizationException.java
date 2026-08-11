package com.forgeos.tools.domain.exception;

import com.forgeos.tools.domain.ToolErrorCode;

public class ToolAuthorizationException extends ToolException {
    public ToolAuthorizationException(ToolErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
