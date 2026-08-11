package com.forgeos.tools.infrastructure.impl;

import com.forgeos.tools.application.Tool;
import com.forgeos.tools.domain.*;
import com.forgeos.tools.domain.exception.ToolValidationException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;

public class GitStatusTool implements Tool {

    private final ToolDefinition definition;

    public GitStatusTool() {
        this.definition = new ToolDefinition();
        definition.setId("git_status");
        definition.setName("Git Status");
        definition.setVersion("1.0");
        definition.setCategory(ToolCategory.GIT);
        definition.setRiskLevel(ToolRiskLevel.LOW);
        definition.setRequiredPermission(ToolPermission.GIT_READ);
    }

    @Override
    public ToolDefinition getDefinition() {
        return definition;
    }

    @Override
    public void validateArguments(ToolRequest request) {
        if (request.getWorkspaceRoot() == null) {
            throw new ToolValidationException("Workspace root must be provided in context.");
        }
        
        File dir = new File(request.getWorkspaceRoot());
        if (!dir.exists() || !dir.isDirectory()) {
            throw new ToolValidationException("Workspace root is invalid.");
        }
    }

    @Override
    public ToolResult execute(ToolRequest request) {
        try {
            ProcessBuilder pb = new ProcessBuilder("git", "status", "--short");
            pb.directory(new File(request.getWorkspaceRoot()));
            Process process = pb.start();
            
            StringBuilder output = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                }
            }
            
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                return ToolResult.success(request.getRequestId(), output.toString());
            } else {
                return ToolResult.failure(request.getRequestId(), ToolErrorCode.EXECUTION_FAILED, "Git status failed with code " + exitCode);
            }
        } catch (Exception e) {
            return ToolResult.failure(request.getRequestId(), ToolErrorCode.EXECUTION_FAILED, e.getMessage());
        }
    }
}
