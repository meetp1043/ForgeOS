package com.forgeos.tools.domain.sandbox;

import com.forgeos.tools.domain.ToolRequest;

/**
 * Defines the boundary for executing tools securely.
 * For Phase 13, this is an interface that will be implemented as NoOp for safe tools,
 * and will evolve into a full containerized execution boundary in Phase 19.
 */
public interface Sandbox {
    
    /**
     * Prepares the isolated environment.
     */
    void prepare(ToolRequest request);

    /**
     * Executes a runnable inside the sandbox boundaries.
     */
    void execute(Runnable task);

    /**
     * Cleans up temporary resources.
     */
    void cleanup();
}
