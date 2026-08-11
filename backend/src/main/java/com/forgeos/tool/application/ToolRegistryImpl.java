package com.forgeos.tool.application;

import com.forgeos.tool.domain.Tool;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ToolRegistryImpl implements ToolRegistry {
    private final Map<String, Tool> tools = new ConcurrentHashMap<>();

    @Override
    public void registerTool(Tool tool) {
        tools.put(tool.getToolId(), tool);
    }

    @Override
    public Optional<Tool> getTool(String toolId) {
        return Optional.ofNullable(tools.get(toolId));
    }
}
