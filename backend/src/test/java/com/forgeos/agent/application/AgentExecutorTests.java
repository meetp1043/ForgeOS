package com.forgeos.agent.application;

import com.forgeos.agent.domain.*;
import com.forgeos.model.application.ModelGateway;
import com.forgeos.model.domain.ModelResponse;
import com.forgeos.tools.application.ToolExecutor;
import com.forgeos.tools.domain.ToolResult;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AgentExecutorTests {

    @Test
    void testAgentReActLoopCompletes() {
        ModelGateway mockGateway = request -> {
            ModelResponse r = new ModelResponse();
            r.setContent("Final answer achieved");
            return r;
        };
        
        ToolExecutor mockToolExec = request -> ToolResult.success(request.getRequestId(), "mock");
        
        DecisionEngine mockDecisionEngine = (exec, output) -> {
            AgentDecision decision = new AgentDecision();
            decision.setType(AgentDecisionType.FINAL_RESULT);
            decision.setContent(output);
            return decision;
        };

        AgentExecutor executor = new AgentExecutorImpl(mockGateway, mockToolExec, mockDecisionEngine);
        
        AgentExecution execution = new AgentExecution();
        execution.setStatus(AgentExecutionStatus.RUNNING);
        execution.setMaxSteps(10);
        execution.setObjective("Do the thing");

        AgentResult result = executor.execute(execution);

        assertEquals(AgentExecutionStatus.COMPLETED, result.getStatus());
        assertEquals("Final answer achieved", result.getSummary());
        assertTrue(execution.getCurrentStep() > 0);
    }
}
