package com.forgeos.identity.domain.security.policy;

import java.util.UUID;

public interface TenantScopedResource {
    UUID getTenantId();
}
