package com.forgeos.tools.application;

import com.forgeos.tools.domain.*;
import com.forgeos.tools.domain.exception.ToolAuthorizationException;
import com.forgeos.tools.infrastructure.impl.MockDangerousTool;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ToolAuthorizationTests {

    @Test
    void testCriticalToolRejectedWithoutSandbox() {
        ToolRegistry registry = new ToolRegistryImpl();
        MockDangerousTool dangerousTool = new MockDangerousTool();
        registry.registerTool(dangerousTool);
        
        ToolAuthorizer authorizer = new ToolAuthorizer();
        ToolExecutor executor = new ToolExecutorImpl(registry, authorizer);

        ToolRequest request = new ToolRequest();
        request.setToolId("mock_dangerous");
        request.setActorId(UUID.randomUUID());
        request.setTenantId(UUID.randomUUID());
        request.setEnvironment(ExecutionEnvironment.DEVELOPMENT);

        ToolResult result = executor.execute(request);
        
        // The executor catches ToolAuthorizationException and returns a failed ToolResult
        assertTrue(result.getStatus() == ToolStatus.FAILED);
        assertTrue(result.getErrorCode() == ToolErrorCode.ENVIRONMENT_NOT_ALLOWED);
        assertTrue(result.getErrorMessage().contains("CRITICAL"));
    }
}
