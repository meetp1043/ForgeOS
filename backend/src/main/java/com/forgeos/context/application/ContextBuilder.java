package com.forgeos.context.application;

import com.forgeos.context.domain.ContextPack;
import com.forgeos.context.domain.ContextRequest;

public interface ContextBuilder {
    /**
     * Builds the complete, validated, secure ContextPack.
     */
    ContextPack build(ContextRequest request);
}
