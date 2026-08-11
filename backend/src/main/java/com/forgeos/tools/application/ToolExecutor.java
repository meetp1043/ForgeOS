package com.forgeos.tools.application;

import com.forgeos.tools.domain.ToolRequest;
import com.forgeos.tools.domain.ToolResult;

/**
 * Entry point for executing any tool in ForgeOS.
 */
public interface ToolExecutor {
    
    /**
     * Executes a tool request.
     * Enforces the 15-step authorization and sandbox lifecycle.
     */
    ToolResult execute(ToolRequest request);
}
