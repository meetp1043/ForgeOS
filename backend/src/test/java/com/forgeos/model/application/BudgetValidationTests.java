package com.forgeos.model.application;

import com.forgeos.model.domain.ModelRequest;
import com.forgeos.model.domain.ModelResponse;
import com.forgeos.model.domain.TokenUsage;
import com.forgeos.model.domain.exception.ModelGatewayException;
import com.forgeos.model.infrastructure.provider.ModelProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BudgetValidationTests {

    private ModelProvider mockProvider;
    private ModelRouter router;
    private ModelGatewayImpl gateway;

    @BeforeEach
    void setUp() {
        mockProvider = mock(ModelProvider.class);
        router = mock(ModelRouter.class);
        gateway = new ModelGatewayImpl(router);
    }

    @Test
    void testBudgetExceededThrowsException() throws Exception {
        ModelRequest request = new ModelRequest();
        request.setUserMessages(List.of("Write me an essay."));
        request.setBudget(0.01); // Very small budget

        when(router.getProviderChain(request)).thenReturn(List.of(mockProvider));

        ModelResponse response = new ModelResponse();
        // A huge response that costs lots of tokens
        response.setTokenUsage(new TokenUsage(500, 10000, 0));
        when(mockProvider.execute(request)).thenReturn(response);

        ModelGatewayException exception = assertThrows(ModelGatewayException.class, () -> {
            gateway.generate(request);
        });

        assertTrue(exception.getMessage().contains("Budget Exceeded"));
    }
}
