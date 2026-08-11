package com.forgeos.tools.infrastructure.impl;

import com.forgeos.tools.application.Tool;
import com.forgeos.tools.domain.*;
import com.forgeos.tools.domain.exception.ToolValidationException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilesystemReadTool implements Tool {

    private final ToolDefinition definition;

    public FilesystemReadTool() {
        this.definition = new ToolDefinition();
        definition.setId("fs_read");
        definition.setName("Filesystem Read");
        definition.setVersion("1.0");
        definition.setCategory(ToolCategory.FILESYSTEM);
        definition.setRiskLevel(ToolRiskLevel.LOW);
        definition.setRequiredPermission(ToolPermission.FILESYSTEM_READ);
    }

    @Override
    public ToolDefinition getDefinition() {
        return definition;
    }

    @Override
    public void validateArguments(ToolRequest request) {
        if (request.getArguments() == null || !request.getArguments().containsKey("path")) {
            throw new ToolValidationException("Argument 'path' is required.");
        }
        if (request.getWorkspaceRoot() == null) {
            throw new ToolValidationException("Workspace root must be provided in context.");
        }
        
        String targetPath = (String) request.getArguments().get("path");
        try {
            Path root = Paths.get(request.getWorkspaceRoot()).toRealPath();
            Path target = root.resolve(targetPath).toRealPath();
            
            if (!target.startsWith(root)) {
                throw new ToolValidationException("Path traversal attempt blocked. Target is outside workspace.");
            }
        } catch (IOException e) {
            throw new ToolValidationException("Invalid path: " + e.getMessage());
        }
    }

    @Override
    public ToolResult execute(ToolRequest request) {
        String targetPath = (String) request.getArguments().get("path");
        try {
            Path root = Paths.get(request.getWorkspaceRoot()).toRealPath();
            Path target = root.resolve(targetPath).toRealPath();
            
            String content = Files.readString(target);
            return ToolResult.success(request.getRequestId(), content);
        } catch (IOException e) {
            return ToolResult.failure(request.getRequestId(), ToolErrorCode.EXECUTION_FAILED, e.getMessage());
        }
    }
}
