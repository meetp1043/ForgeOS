package com.forgeos.tools.application;

import com.forgeos.tools.domain.*;
import com.forgeos.tools.infrastructure.impl.FilesystemReadTool;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PathTraversalTests {

    @TempDir
    Path tempDir;

    @Test
    void testPathTraversalBlocked() {
        ToolRegistry registry = new ToolRegistryImpl();
        FilesystemReadTool readTool = new FilesystemReadTool();
        registry.registerTool(readTool);

        ToolAuthorizer authorizer = new ToolAuthorizer();
        ToolExecutor executor = new ToolExecutorImpl(registry, authorizer);

        ToolRequest request = new ToolRequest();
        request.setToolId("fs_read");
        request.setActorId(UUID.randomUUID());
        request.setTenantId(UUID.randomUUID());
        request.setWorkspaceRoot(tempDir.toString());
        
        Map<String, Object> args = new HashMap<>();
        // Attempt path traversal escaping the workspace root
        args.put("path", "../../../../etc/passwd");
        request.setArguments(args);

        ToolResult result = executor.execute(request);

        assertEquals(ToolStatus.FAILED, result.getStatus());
        assertEquals(ToolErrorCode.VALIDATION_FAILED, result.getErrorCode());
        assertTrue(result.getErrorMessage().contains("outside workspace"));
    }
}
