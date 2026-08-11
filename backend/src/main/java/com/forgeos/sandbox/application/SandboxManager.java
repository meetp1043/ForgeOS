package com.forgeos.sandbox.application;

import com.forgeos.sandbox.domain.Execution;
import com.forgeos.sandbox.domain.ExecutionCommand;
import com.forgeos.sandbox.domain.ExecutionResult;
import com.forgeos.sandbox.domain.Sandbox;

import java.util.UUID;

public interface SandboxManager {
    Sandbox createSandbox(UUID tenantId, UUID workspaceId);
    ExecutionResult execute(Sandbox sandbox, ExecutionCommand command);
    void validateWorkspacePath(Sandbox sandbox, String relativePath) throws SecurityException;
}
