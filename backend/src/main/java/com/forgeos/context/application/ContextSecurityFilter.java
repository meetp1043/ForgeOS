package com.forgeos.context.application;

import com.forgeos.context.domain.ContextItem;
import com.forgeos.context.domain.ContextRequest;

import java.util.List;

public interface ContextSecurityFilter {
    /**
     * Evaluates a list of context items against the requesting agent's permissions and the tenant boundaries.
     * Returns a new list containing only the explicitly permitted items.
     */
    List<ContextItem> filter(List<ContextItem> items, ContextRequest request);
}
