package com.forgeos.tools.application;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ToolRegistryImpl implements ToolRegistry {

    private final Map<String, Tool> tools = new ConcurrentHashMap<>();

    @Override
    public Optional<Tool> getTool(String toolId) {
        return Optional.ofNullable(tools.get(toolId));
    }

    @Override
    public void registerTool(Tool tool) {
        tools.put(tool.getDefinition().getId(), tool);
    }
}
