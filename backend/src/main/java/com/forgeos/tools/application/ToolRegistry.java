package com.forgeos.tools.application;

import java.util.Optional;

public interface ToolRegistry {
    
    /**
     * Retrieves a tool by ID.
     */
    Optional<Tool> getTool(String toolId);
    
    /**
     * Registers a new tool in the system.
     */
    void registerTool(Tool tool);
}
