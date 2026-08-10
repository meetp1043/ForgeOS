package com.forgeos.execution.domain;

public record ExecutionResult(ExecutionId executionId, ExecutionStatus finalStatus, String outputJson) {}
