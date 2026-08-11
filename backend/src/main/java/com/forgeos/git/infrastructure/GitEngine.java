package com.forgeos.git.infrastructure;

import com.forgeos.git.domain.ChangeSet;
import com.forgeos.git.domain.Workspace;
import java.util.UUID;

public interface GitEngine {
    void cloneRepository(Workspace workspace, String cloneUrl);
    void createBranch(Workspace workspace, String branchName);
    ChangeSet diff(Workspace workspace);
    void commit(Workspace workspace, String message, ChangeSet changeSet);
    void push(Workspace workspace, String remote, String branch);
}
