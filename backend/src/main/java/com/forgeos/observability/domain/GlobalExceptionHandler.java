package com.forgeos.observability.domain;

import io.micrometer.tracing.Span;
import io.micrometer.tracing.Tracer;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.UUID;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private final Tracer tracer;

    public GlobalExceptionHandler(Tracer tracer) {
        this.tracer = tracer;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<NormalizedErrorResponse> handleGenericException(Exception ex, HttpServletRequest request) {
        return buildErrorResponse(ex, request, HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_ERROR");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<NormalizedErrorResponse> handleIllegalArgument(IllegalArgumentException ex, HttpServletRequest request) {
        return buildErrorResponse(ex, request, HttpStatus.BAD_REQUEST, "VALIDATION_ERROR");
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<NormalizedErrorResponse> handleIllegalState(IllegalStateException ex, HttpServletRequest request) {
        return buildErrorResponse(ex, request, HttpStatus.CONFLICT, "CONFLICT");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<NormalizedErrorResponse> handleAccessDenied(AccessDeniedException ex, HttpServletRequest request) {
        return buildErrorResponse(ex, request, HttpStatus.FORBIDDEN, "AUTHORIZATION_ERROR");
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<NormalizedErrorResponse> handleBadCredentials(BadCredentialsException ex, HttpServletRequest request) {
        return buildErrorResponse(ex, request, HttpStatus.UNAUTHORIZED, "AUTHENTICATION_ERROR");
    }

    private ResponseEntity<NormalizedErrorResponse> buildErrorResponse(Exception ex, HttpServletRequest request, HttpStatus status, String errorCode) {
        String errorId = UUID.randomUUID().toString();
        String traceId = getTraceId();

        // Log the full stack trace with structured context on the server side
        log.error("Error {} [{}] during operation on {}: {}", errorCode, errorId, request.getRequestURI(), ex.getMessage(), ex);

        NormalizedErrorResponse response = new NormalizedErrorResponse();
        response.setErrorId(errorId);
        response.setTraceId(traceId);
        response.setErrorCode(errorCode);
        response.setMessage(ex.getMessage());
        response.setOperation(request.getMethod() + " " + request.getRequestURI());

        return ResponseEntity.status(status).body(response);
    }

    private String getTraceId() {
        if (tracer == null) return "no-trace";
        Span currentSpan = tracer.currentSpan();
        if (currentSpan != null) {
            return currentSpan.context().traceId();
        }
        return "no-trace";
    }
}
