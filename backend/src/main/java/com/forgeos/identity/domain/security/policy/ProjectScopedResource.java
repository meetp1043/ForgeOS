package com.forgeos.identity.domain.security.policy;

import java.util.UUID;

public interface ProjectScopedResource {
    UUID getProjectId();
}
