package com.forgeos.tools.infrastructure.impl;

import com.forgeos.tools.application.Tool;
import com.forgeos.tools.domain.*;
import com.forgeos.tools.domain.exception.ToolValidationException;

public class MockDangerousTool implements Tool {

    private final ToolDefinition definition;

    public MockDangerousTool() {
        this.definition = new ToolDefinition();
        definition.setId("mock_dangerous");
        definition.setName("Mock Dangerous Tool");
        definition.setVersion("1.0");
        definition.setCategory(ToolCategory.OTHER);
        definition.setRiskLevel(ToolRiskLevel.CRITICAL);
        definition.setRequiredPermission(ToolPermission.TERMINAL_EXECUTE);
    }

    @Override
    public ToolDefinition getDefinition() {
        return definition;
    }

    @Override
    public void validateArguments(ToolRequest request) {
        // Mock validation always passes
    }

    @Override
    public ToolResult execute(ToolRequest request) {
        return ToolResult.success(request.getRequestId(), "If you see this, authorization failed to block a CRITICAL tool!");
    }
}
