package com.forgeos.tool.application;

import com.forgeos.tool.domain.Tool;
import java.util.Optional;

public interface ToolRegistry {
    void registerTool(Tool tool);
    Optional<Tool> getTool(String toolId);
}
