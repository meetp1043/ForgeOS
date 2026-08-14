package com.forgeos.observability.domain;

import io.micrometer.tracing.Span;
import io.micrometer.tracing.TraceContext;
import io.micrometer.tracing.Tracer;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GlobalExceptionHandlerTests {

    private GlobalExceptionHandler exceptionHandler;
    private Tracer tracer;
    private HttpServletRequest request;

    @BeforeEach
    void setUp() {
        tracer = mock(Tracer.class);
        exceptionHandler = new GlobalExceptionHandler(tracer);
        request = mock(HttpServletRequest.class);
    }

    @Test
    void testHandleIllegalArgument() {
        when(request.getRequestURI()).thenReturn("/api/test");
        when(request.getMethod()).thenReturn("POST");

        Span span = mock(Span.class);
        TraceContext context = mock(TraceContext.class);
        when(tracer.currentSpan()).thenReturn(span);
        when(span.context()).thenReturn(context);
        when(context.traceId()).thenReturn("trace-1234");

        ResponseEntity<NormalizedErrorResponse> responseEntity = exceptionHandler.handleIllegalArgument(
                new IllegalArgumentException("Invalid input"), request);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        NormalizedErrorResponse body = responseEntity.getBody();
        assertNotNull(body);
        assertEquals("VALIDATION_ERROR", body.getErrorCode());
        assertEquals("Invalid input", body.getMessage());
        assertEquals("trace-1234", body.getTraceId());
        assertEquals("POST /api/test", body.getOperation());
        assertNotNull(body.getErrorId());
    }

    @Test
    void testHandleGenericExceptionWithoutTrace() {
        when(request.getRequestURI()).thenReturn("/api/unknown");
        when(request.getMethod()).thenReturn("GET");
        when(tracer.currentSpan()).thenReturn(null);

        ResponseEntity<NormalizedErrorResponse> responseEntity = exceptionHandler.handleGenericException(
                new RuntimeException("Something broke"), request);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        NormalizedErrorResponse body = responseEntity.getBody();
        assertNotNull(body);
        assertEquals("INTERNAL_ERROR", body.getErrorCode());
        assertEquals("no-trace", body.getTraceId());
    }
}
