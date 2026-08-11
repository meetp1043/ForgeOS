package com.forgeos.git.application;

import com.forgeos.git.domain.Repository;
import com.forgeos.git.domain.Workspace;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.UUID;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class WorkspaceManagerImpl implements WorkspaceManager {

    private final String workspaceRoot = "/tmp/forgeos/workspaces"; // Simplified for demonstration

    @Override
    public Workspace createWorkspace(Repository repository, UUID taskId) {
        Workspace workspace = new Workspace();
        workspace.setWorkspaceId(UUID.randomUUID());
        workspace.setTenantId(repository.getTenantId());
        workspace.setRepositoryId(repository.getRepositoryId());
        workspace.setTaskId(taskId);
        
        // Strict path convention to prevent collisions/traversals
        String localPath = Paths.get(workspaceRoot, 
            repository.getTenantId().toString(), 
            repository.getRepositoryId().toString(), 
            workspace.getWorkspaceId().toString()).toString();
            
        workspace.setLocalPath(localPath);
        workspace.setStatus("READY");
        workspace.setCreatedAt(OffsetDateTime.now());
        
        return workspace;
    }

    @Override
    public void validateWorkspacePath(Workspace workspace, String relativePath) throws SecurityException {
        Path rootPath = Paths.get(workspace.getLocalPath()).normalize();
        Path targetPath = rootPath.resolve(relativePath).normalize();
        
        if (!targetPath.startsWith(rootPath)) {
            throw new SecurityException("Path traversal detected! Attempted to access files outside the workspace bounds.");
        }
    }
}
