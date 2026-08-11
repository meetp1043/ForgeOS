package com.forgeos.sandbox.application;

import com.forgeos.sandbox.domain.ExecutionCommand;
import com.forgeos.sandbox.domain.ExecutionResult;
import com.forgeos.sandbox.domain.ExecutionStatus;
import com.forgeos.sandbox.domain.Sandbox;
import com.forgeos.sandbox.infrastructure.ProcessSandboxRuntime;
import com.forgeos.sandbox.infrastructure.SandboxRuntime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SecureExecutionSandboxTests {

    private SandboxManager sandboxManager;

    @BeforeEach
    void setUp() {
        ExecutionPolicyEngine policyEngine = new ExecutionPolicyEngineImpl();
        SandboxRuntime runtime = new ProcessSandboxRuntime();
        sandboxManager = new SandboxManagerImpl(policyEngine, runtime);
    }

    @Test
    void testAllowedCommandExecutesSuccessfully() {
        Sandbox sandbox = sandboxManager.createSandbox(UUID.randomUUID(), UUID.randomUUID());
        
        // This is safe because 'mvn' is allowlisted and arguments are safe.
        ExecutionCommand command = new ExecutionCommand("mvn", List.of("clean", "test"), ".", 10000);
        
        ExecutionResult result = sandboxManager.execute(sandbox, command);
        
        assertEquals(ExecutionStatus.COMPLETED, result.getStatus());
        assertEquals(0, result.getExitCode());
    }

    @Test
    void testBlockedCommandIsRejectedByPolicyEngine() {
        Sandbox sandbox = sandboxManager.createSandbox(UUID.randomUUID(), UUID.randomUUID());
        
        // 'rm' is not on the allowlist.
        ExecutionCommand command = new ExecutionCommand("rm", List.of("-rf", "/"), ".", 10000);
        
        ExecutionResult result = sandboxManager.execute(sandbox, command);
        
        assertEquals(ExecutionStatus.SECURITY_BLOCKED, result.getStatus());
        assertEquals(126, result.getExitCode());
        assertTrue(result.getStderrReference().contains("is not on the sandbox allowlist"));
    }

    @Test
    void testCommandInjectionIsBlocked() {
        Sandbox sandbox = sandboxManager.createSandbox(UUID.randomUUID(), UUID.randomUUID());
        
        // 'mvn' is allowlisted, but the argument attempts injection using `&&`.
        ExecutionCommand command = new ExecutionCommand("mvn", List.of("clean", "&&", "cat", "/etc/passwd"), ".", 10000);
        
        ExecutionResult result = sandboxManager.execute(sandbox, command);
        
        assertEquals(ExecutionStatus.SECURITY_BLOCKED, result.getStatus());
        assertEquals(126, result.getExitCode());
        assertTrue(result.getStderrReference().contains("Potential shell injection detected"));
    }

    @Test
    void testPathTraversalIsBlocked() {
        Sandbox sandbox = sandboxManager.createSandbox(UUID.randomUUID(), UUID.randomUUID());
        
        // Command attempts to escape the working directory
        ExecutionCommand command = new ExecutionCommand("mvn", List.of("clean"), "../../outside/dir", 10000);
        
        ExecutionResult result = sandboxManager.execute(sandbox, command);
        
        assertEquals(ExecutionStatus.SECURITY_BLOCKED, result.getStatus());
        assertEquals(126, result.getExitCode());
        assertTrue(result.getStderrReference().contains("Path traversal detected"));
    }
}
