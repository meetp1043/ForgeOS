package com.forgeos.git.application;

import com.forgeos.git.domain.Repository;
import com.forgeos.git.domain.Workspace;

import java.util.UUID;

public interface WorkspaceManager {
    Workspace createWorkspace(Repository repository, UUID taskId);
    void validateWorkspacePath(Workspace workspace, String relativePath) throws SecurityException;
}
