package com.forgeos.tools.infrastructure.impl;

import com.forgeos.tools.application.Tool;
import com.forgeos.tools.domain.*;
import com.forgeos.tools.domain.exception.ToolValidationException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilesystemWriteTool implements Tool {

    private final ToolDefinition definition;

    public FilesystemWriteTool() {
        this.definition = new ToolDefinition();
        definition.setId("fs_write");
        definition.setName("Filesystem Write");
        definition.setVersion("1.0");
        definition.setCategory(ToolCategory.FILESYSTEM);
        definition.setRiskLevel(ToolRiskLevel.MEDIUM);
        definition.setRequiredPermission(ToolPermission.FILESYSTEM_WRITE);
    }

    @Override
    public ToolDefinition getDefinition() {
        return definition;
    }

    @Override
    public void validateArguments(ToolRequest request) {
        if (request.getArguments() == null || !request.getArguments().containsKey("path") || !request.getArguments().containsKey("content")) {
            throw new ToolValidationException("Arguments 'path' and 'content' are required.");
        }
        if (request.getWorkspaceRoot() == null) {
            throw new ToolValidationException("Workspace root must be provided in context.");
        }
        
        String targetPath = (String) request.getArguments().get("path");
        try {
            Path root = Paths.get(request.getWorkspaceRoot()).toRealPath();
            // We use normalize() before toRealPath() because the file might not exist yet for writing
            Path target = root.resolve(targetPath).normalize();
            
            if (!target.startsWith(root)) {
                throw new ToolValidationException("Path traversal attempt blocked. Target is outside workspace.");
            }
        } catch (IOException e) {
            throw new ToolValidationException("Invalid workspace root: " + e.getMessage());
        }
    }

    @Override
    public ToolResult execute(ToolRequest request) {
        String targetPath = (String) request.getArguments().get("path");
        String content = (String) request.getArguments().get("content");
        try {
            Path root = Paths.get(request.getWorkspaceRoot()).toRealPath();
            Path target = root.resolve(targetPath).normalize();
            
            // Ensure parent directories exist
            Files.createDirectories(target.getParent());
            Files.writeString(target, content);
            
            return ToolResult.success(request.getRequestId(), "File written successfully.");
        } catch (IOException e) {
            return ToolResult.failure(request.getRequestId(), ToolErrorCode.EXECUTION_FAILED, e.getMessage());
        }
    }
}
