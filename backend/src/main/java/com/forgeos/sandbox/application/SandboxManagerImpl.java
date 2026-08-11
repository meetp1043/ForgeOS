package com.forgeos.sandbox.application;

import com.forgeos.sandbox.domain.ExecutionCommand;
import com.forgeos.sandbox.domain.ExecutionResult;
import com.forgeos.sandbox.domain.ExecutionStatus;
import com.forgeos.sandbox.domain.Sandbox;
import com.forgeos.sandbox.infrastructure.SandboxRuntime;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.OffsetDateTime;
import java.util.UUID;

@Service
public class SandboxManagerImpl implements SandboxManager {

    private final ExecutionPolicyEngine policyEngine;
    private final SandboxRuntime runtime;
    private final String workspaceRoot = "/tmp/forgeos/sandboxes";

    public SandboxManagerImpl(ExecutionPolicyEngine policyEngine, SandboxRuntime runtime) {
        this.policyEngine = policyEngine;
        this.runtime = runtime;
    }

    @Override
    public Sandbox createSandbox(UUID tenantId, UUID workspaceId) {
        Sandbox sandbox = new Sandbox();
        sandbox.setSandboxId(UUID.randomUUID());
        sandbox.setRuntimeType("PROCESS"); // Defaulting to process for demonstration
        sandbox.setStatus("READY");
        sandbox.setCreatedAt(OffsetDateTime.now());
        
        // Isolate filesystem workspace
        String localPath = Paths.get(workspaceRoot, tenantId.toString(), workspaceId.toString()).toString();
        sandbox.setWorkspaceLocalPath(localPath);
        
        return sandbox;
    }

    @Override
    public void validateWorkspacePath(Sandbox sandbox, String relativePath) throws SecurityException {
        Path rootPath = Paths.get(sandbox.getWorkspaceLocalPath()).normalize();
        Path targetPath = rootPath.resolve(relativePath).normalize();
        
        if (!targetPath.startsWith(rootPath)) {
            throw new SecurityException("Path traversal detected! Attempted to access files outside the sandbox workspace.");
        }
    }

    @Override
    public ExecutionResult execute(Sandbox sandbox, ExecutionCommand command) {
        try {
            // 1. Evaluate command against policy BEFORE runtime
            policyEngine.evaluate(command);
            
            // 2. Enforce Working Directory is within sandbox bounds
            validateWorkspacePath(sandbox, command.getWorkingDirectory());
            
            // 3. Delegate to isolated runtime
            return runtime.execute(sandbox, command);
            
        } catch (SecurityException e) {
            ExecutionResult result = new ExecutionResult();
            result.setStatus(ExecutionStatus.SECURITY_BLOCKED);
            result.setExitCode(126);
            result.setStderrReference(e.getMessage());
            return result;
        }
    }
}
