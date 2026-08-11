package com.forgeos.sandbox.application;

import com.forgeos.sandbox.domain.ExecutionCommand;

public interface ExecutionPolicyEngine {
    void evaluate(ExecutionCommand command) throws SecurityException;
}
