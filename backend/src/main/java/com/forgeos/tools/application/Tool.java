package com.forgeos.tools.application;

import com.forgeos.tools.domain.ToolDefinition;
import com.forgeos.tools.domain.ToolRequest;
import com.forgeos.tools.domain.ToolResult;

/**
 * Represents an executable tool in the system.
 */
public interface Tool {
    
    /**
     * Defines the static metadata for this tool (ID, Risk Level, required permissions).
     */
    ToolDefinition getDefinition();

    /**
     * Validates the tool arguments provided by the AI agent.
     * MUST throw ToolValidationException if arguments are invalid or unsafe.
     */
    void validateArguments(ToolRequest request);

    /**
     * Executes the tool's core logic.
     * The framework guarantees this is only called AFTER authorization, risk assessment, and validation.
     */
    ToolResult execute(ToolRequest request);
}
