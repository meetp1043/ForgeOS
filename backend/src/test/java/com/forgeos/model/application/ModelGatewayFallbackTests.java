package com.forgeos.model.application;

import com.forgeos.model.domain.ModelError;
import com.forgeos.model.domain.ModelPolicy;
import com.forgeos.model.domain.ModelRequest;
import com.forgeos.model.domain.ModelResponse;
import com.forgeos.model.domain.exception.ModelGatewayException;
import com.forgeos.model.domain.exception.ProviderException;
import com.forgeos.model.infrastructure.provider.ModelProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ModelGatewayFallbackTests {

    private ModelProvider primaryProvider;
    private ModelProvider fallbackProvider;
    private ModelRouter router;
    private ModelGatewayImpl gateway;

    @BeforeEach
    void setUp() {
        primaryProvider = mock(ModelProvider.class);
        fallbackProvider = mock(ModelProvider.class);
        
        when(primaryProvider.getProviderName()).thenReturn("OPENAI");
        when(fallbackProvider.getProviderName()).thenReturn("MOCK");

        router = mock(ModelRouter.class);
        gateway = new ModelGatewayImpl(router);
    }

    @Test
    void testFallbackOnTransientError() throws Exception {
        ModelRequest request = new ModelRequest();
        request.setUserMessages(List.of("Hello"));
        
        when(router.getProviderChain(request)).thenReturn(List.of(primaryProvider, fallbackProvider));

        // Primary provider throws a RATE_LIMIT (retryable)
        when(primaryProvider.execute(request)).thenThrow(new ProviderException("Rate Limit", ModelError.RATE_LIMIT, true));
        
        ModelResponse fallbackResponse = new ModelResponse();
        fallbackResponse.setContent("Fallback success");
        when(fallbackProvider.execute(request)).thenReturn(fallbackResponse);

        ModelResponse result = gateway.generate(request);
        
        assertNotNull(result);
        assertEquals("Fallback success", result.getContent());
        assertTrue(result.isFallbackUsed());
        
        verify(primaryProvider, times(1)).execute(request);
        verify(fallbackProvider, times(1)).execute(request);
    }

    @Test
    void testNoFallbackOnNonRetryableError() throws Exception {
        ModelRequest request = new ModelRequest();
        request.setUserMessages(List.of("Hello"));
        
        when(router.getProviderChain(request)).thenReturn(List.of(primaryProvider, fallbackProvider));

        // Primary provider throws an AUTHENTICATION_ERROR (non-retryable)
        when(primaryProvider.execute(request)).thenThrow(new ProviderException("Invalid API Key", ModelError.AUTHENTICATION_ERROR, false));

        ModelGatewayException exception = assertThrows(ModelGatewayException.class, () -> {
            gateway.generate(request);
        });
        
        assertTrue(exception.getMessage().contains("Non-retryable provider error"));
        
        verify(primaryProvider, times(1)).execute(request);
        verify(fallbackProvider, never()).execute(any());
    }

    @Test
    void testThrowsWhenAllProvidersFail() throws Exception {
        ModelRequest request = new ModelRequest();
        request.setUserMessages(List.of("Hello"));
        
        when(router.getProviderChain(request)).thenReturn(List.of(primaryProvider, fallbackProvider));

        when(primaryProvider.execute(request)).thenThrow(new ProviderException("Timeout 1", ModelError.TIMEOUT, true));
        when(fallbackProvider.execute(request)).thenThrow(new ProviderException("Timeout 2", ModelError.TIMEOUT, true));

        ModelGatewayException exception = assertThrows(ModelGatewayException.class, () -> {
            gateway.generate(request);
        });

        assertTrue(exception.getMessage().contains("All providers in the routing chain failed"));
        assertTrue(exception.getMessage().contains("Timeout 2"));
    }
}
