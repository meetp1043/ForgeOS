package com.forgeos.organization.domain.security;

import java.util.UUID;

public class TenantContextHolder {

    private static final ThreadLocal<UUID> TENANT_ID_HOLDER = new ThreadLocal<>();

    public static void setTenantId(UUID tenantId) {
        TENANT_ID_HOLDER.set(tenantId);
    }

    public static UUID getTenantId() {
        return TENANT_ID_HOLDER.get();
    }

    public static void clear() {
        TENANT_ID_HOLDER.remove();
    }
}
