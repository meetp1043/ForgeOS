package com.forgeos.git.application;

import com.forgeos.git.domain.ChangeSet;
import com.forgeos.git.domain.Workspace;

public interface SecretScanner {
    /**
     * Scans the workspace or change set for leaked credentials.
     * Throws a SecurityException if a secret is found, which blocks the commit/push.
     */
    void scan(Workspace workspace, ChangeSet changeSet) throws SecurityException;
}
