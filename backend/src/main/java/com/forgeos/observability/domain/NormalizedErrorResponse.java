package com.forgeos.observability.domain;

import java.time.Instant;

public class NormalizedErrorResponse {
    
    private String errorId;
    private String traceId;
    private String errorCode;
    private String message;
    private String operation;
    private Instant timestamp;

    public NormalizedErrorResponse() {
        this.timestamp = Instant.now();
    }

    public String getErrorId() { return errorId; }
    public void setErrorId(String errorId) { this.errorId = errorId; }

    public String getTraceId() { return traceId; }
    public void setTraceId(String traceId) { this.traceId = traceId; }

    public String getErrorCode() { return errorCode; }
    public void setErrorCode(String errorCode) { this.errorCode = errorCode; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getOperation() { return operation; }
    public void setOperation(String operation) { this.operation = operation; }

    public Instant getTimestamp() { return timestamp; }
    public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }
}
