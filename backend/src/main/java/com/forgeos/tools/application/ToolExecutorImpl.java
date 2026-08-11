package com.forgeos.tools.application;

import com.forgeos.tools.domain.ToolErrorCode;
import com.forgeos.tools.domain.ToolRequest;
import com.forgeos.tools.domain.ToolResult;
import com.forgeos.tools.domain.exception.ToolAuthorizationException;
import com.forgeos.tools.domain.exception.ToolException;
import com.forgeos.tools.domain.exception.ToolValidationException;
import com.forgeos.tools.domain.sandbox.NoOpSandbox;
import com.forgeos.tools.domain.sandbox.Sandbox;
import org.springframework.stereotype.Service;

@Service
public class ToolExecutorImpl implements ToolExecutor {

    private final ToolRegistry registry;
    private final ToolAuthorizer authorizer;

    public ToolExecutorImpl(ToolRegistry registry, ToolAuthorizer authorizer) {
        this.registry = registry;
        this.authorizer = authorizer;
    }

    @Override
    public ToolResult execute(ToolRequest request) {
        long startTime = System.currentTimeMillis();

        try {
            Tool tool = registry.getTool(request.getToolId())
                    .orElseThrow(() -> new ToolException(ToolErrorCode.TOOL_NOT_FOUND, "Tool not found: " + request.getToolId()));

            // 1. Authorize
            authorizer.authorize(request, tool.getDefinition());

            // 2. Validate arguments
            tool.validateArguments(request);

            // 3. Sandbox Preparation
            Sandbox sandbox = determineSandbox(tool);
            sandbox.prepare(request);

            // 4. Execution
            final ToolResult[] resultRef = new ToolResult[1];
            sandbox.execute(() -> {
                resultRef[0] = tool.execute(request);
            });

            // 5. Cleanup
            sandbox.cleanup();

            ToolResult result = resultRef[0];
            result.setDurationMs(System.currentTimeMillis() - startTime);
            return result;

        } catch (ToolAuthorizationException e) {
            return buildErrorResult(request, e.getErrorCode(), e.getMessage(), startTime);
        } catch (ToolValidationException e) {
            return buildErrorResult(request, ToolErrorCode.VALIDATION_FAILED, e.getMessage(), startTime);
        } catch (ToolException e) {
            return buildErrorResult(request, e.getErrorCode(), e.getMessage(), startTime);
        } catch (Exception e) {
            return buildErrorResult(request, ToolErrorCode.EXECUTION_FAILED, "Unexpected error: " + e.getMessage(), startTime);
        }
    }

    private Sandbox determineSandbox(Tool tool) {
        // In Phase 13, safe tools get a NoOpSandbox. 
        // Dangerous tools would get a Docker/Kata container sandbox in Phase 19.
        return new NoOpSandbox();
    }

    private ToolResult buildErrorResult(ToolRequest request, ToolErrorCode code, String message, long startTime) {
        ToolResult result = ToolResult.failure(request.getRequestId(), code, message);
        result.setDurationMs(System.currentTimeMillis() - startTime);
        return result;
    }
}
