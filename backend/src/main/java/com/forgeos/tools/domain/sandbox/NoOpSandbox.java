package com.forgeos.tools.domain.sandbox;

import com.forgeos.tools.domain.ToolRequest;

/**
 * A pass-through sandbox used only for Tools with an explicit LOW or MEDIUM risk classification
 * that implement internal workspace path canonicalization protections natively in Java.
 */
public class NoOpSandbox implements Sandbox {

    @Override
    public void prepare(ToolRequest request) {
        // No physical isolation to prepare
    }

    @Override
    public void execute(Runnable task) {
        // Run directly in the current thread context
        task.run();
    }

    @Override
    public void cleanup() {
        // No resources to clean
    }
}
