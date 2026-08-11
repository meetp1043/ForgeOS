package com.forgeos.sandbox.application;

import com.forgeos.sandbox.domain.ExecutionCommand;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExecutionPolicyEngineImpl implements ExecutionPolicyEngine {

    private static final List<String> ALLOWLIST = List.of(
        "mvn", "gradle", "npm", "pnpm", "yarn", "node", "java", "python", "pytest", "go", "cargo", "git"
    );

    private static final List<String> DANGEROUS_ARGS = List.of(
        "rm -rf", "&&", ";", "|", ">", ">>", "`"
    );

    @Override
    public void evaluate(ExecutionCommand command) throws SecurityException {
        // 1. Allowlist Enforcement
        if (!ALLOWLIST.contains(command.getExecutable())) {
            throw new SecurityException("Command blocked: Executable '" + command.getExecutable() + "' is not on the sandbox allowlist.");
        }

        // 2. Argument Validation (Prevent Command Injection)
        for (String arg : command.getArguments()) {
            for (String dangerous : DANGEROUS_ARGS) {
                if (arg.contains(dangerous)) {
                    throw new SecurityException("Command blocked: Potential shell injection detected in argument.");
                }
            }
        }
    }
}
