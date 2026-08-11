package com.forgeos.sandbox.infrastructure;

import com.forgeos.sandbox.domain.ExecutionCommand;
import com.forgeos.sandbox.domain.ExecutionResult;
import com.forgeos.sandbox.domain.Sandbox;

public interface SandboxRuntime {
    ExecutionResult execute(Sandbox sandbox, ExecutionCommand command);
}
