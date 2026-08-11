package com.forgeos.model.application;

import com.forgeos.model.domain.ModelPolicy;
import com.forgeos.model.domain.ModelRequest;
import com.forgeos.model.domain.ModelResponse;
import com.forgeos.model.domain.exception.ModelGatewayException;
import com.forgeos.model.infrastructure.provider.MockModelProvider;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ModelGatewayTests {

    @Test
    void testMockProviderRouting() {
        MockModelProvider mockProvider = new MockModelProvider();
        ModelRouter router = new ModelRouter(List.of(mockProvider));
        ModelGateway gateway = new ModelGatewayImpl(router);

        ModelRequest request = new ModelRequest();
        request.setUserMessages(Collections.singletonList("Hello ForgeOS"));
        request.setPolicy(ModelPolicy.MOCK_ONLY);

        ModelResponse response = gateway.generate(request);
        
        assertNotNull(response);
        assertEquals("MOCK", response.getProvider());
        assertEquals("This is a mock response to: Hello ForgeOS", response.getContent());
    }

    @Test
    void testEmptyMessageRejection() {
        MockModelProvider mockProvider = new MockModelProvider();
        ModelRouter router = new ModelRouter(List.of(mockProvider));
        ModelGateway gateway = new ModelGatewayImpl(router);

        ModelRequest request = new ModelRequest();
        request.setPolicy(ModelPolicy.MOCK_ONLY);

        assertThrows(ModelGatewayException.class, () -> {
            gateway.generate(request);
        });
    }
}
