package com.forgeos.sandbox.infrastructure;

import com.forgeos.sandbox.domain.ExecutionCommand;
import com.forgeos.sandbox.domain.ExecutionResult;
import com.forgeos.sandbox.domain.ExecutionStatus;
import com.forgeos.sandbox.domain.Sandbox;
import org.springframework.stereotype.Service;

@Service
public class ProcessSandboxRuntime implements SandboxRuntime {

    @Override
    public ExecutionResult execute(Sandbox sandbox, ExecutionCommand command) {
        // NOTE: In a real environment, this would use ProcessBuilder with strict
        // restrictions, or ideally delegate to DockerSandboxRuntime.
        // For deterministic testing/demonstration without full shell access:
        
        ExecutionResult result = new ExecutionResult();
        result.setExecutionId(sandbox.getExecutionId());
        
        if (command.getExecutable().equals("rm") && command.getArguments().contains("-rf")) {
            // Failsafe in case policy engine missed it (Defense in depth)
            result.setStatus(ExecutionStatus.SECURITY_BLOCKED);
            result.setExitCode(126);
            result.setStderrReference("SECURITY VIOLATION: Dangerous command prevented at runtime.");
            return result;
        }

        result.setStatus(ExecutionStatus.COMPLETED);
        result.setExitCode(0);
        result.setStdoutReference("Simulated successful execution of " + command.getExecutable());
        
        return result;
    }
}
