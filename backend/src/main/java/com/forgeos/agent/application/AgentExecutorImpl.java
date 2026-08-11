package com.forgeos.agent.application;

import com.forgeos.agent.domain.AgentDecision;
import com.forgeos.agent.domain.AgentExecution;
import com.forgeos.agent.domain.AgentExecutionStatus;
import com.forgeos.agent.domain.AgentResult;
import com.forgeos.model.application.ModelGateway;
import com.forgeos.model.domain.ModelRequest;
import com.forgeos.model.domain.ModelResponse;
import com.forgeos.tools.application.ToolExecutor;
import com.forgeos.tools.domain.ToolRequest;
import com.forgeos.tools.domain.ToolResult;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AgentExecutorImpl implements AgentExecutor {

    private final ModelGateway modelGateway;
    private final ToolExecutor toolExecutor;
    private final DecisionEngine decisionEngine;
    // We break the circular dependency logically by passing supervisor to check limits
    // In Spring, we'd use ApplicationEventPublisher, but for architecture we can inject it lazily or check limits here.

    public AgentExecutorImpl(ModelGateway modelGateway, ToolExecutor toolExecutor, DecisionEngine decisionEngine) {
        this.modelGateway = modelGateway;
        this.toolExecutor = toolExecutor;
        this.decisionEngine = decisionEngine;
    }

    @Override
    public AgentResult execute(AgentExecution execution) {
        AgentResult finalResult = new AgentResult();
        finalResult.setExecutionId(execution.getId());

        try {
            while (execution.getStatus() == AgentExecutionStatus.RUNNING || execution.getStatus() == AgentExecutionStatus.CREATED) {
                
                // 1. Enforce Limits (Normally called via Supervisor)
                if (execution.getCurrentStep() >= execution.getMaxSteps()) {
                    throw new RuntimeException("Max steps exceeded.");
                }
                execution.setCurrentStep(execution.getCurrentStep() + 1);

                // 2. Reason (Call Model Gateway)
                ModelRequest request = new ModelRequest();
                request.setUserMessages(Collections.singletonList("Objective: " + execution.getObjective()));
                // Add context/history here...
                
                ModelResponse response = modelGateway.generate(request);
                
                // 3. Decide
                AgentDecision decision = decisionEngine.parseDecision(execution, response.getContent());
                
                // 4. Act
                switch (decision.getType()) {
                    case FINAL_RESULT:
                        execution.setStatus(AgentExecutionStatus.COMPLETED);
                        finalResult.setStatus(AgentExecutionStatus.COMPLETED);
                        finalResult.setSummary(decision.getContent());
                        return finalResult;
                        
                    case TOOL_REQUEST:
                        ToolRequest toolRequest = new ToolRequest();
                        toolRequest.setToolId(decision.getTargetToolOrAgent());
                        toolRequest.setArguments(decision.getArguments());
                        toolRequest.setTenantId(execution.getTenantId());
                        toolRequest.setWorkspaceRoot(execution.getWorkspacePath());
                        toolRequest.setActorId(execution.getAgentId()); // Agent is the actor
                        
                        ToolResult toolResult = toolExecutor.execute(toolRequest);
                        // In real code, we append toolResult to the message history to loop back to Step 2
                        if (toolResult.getStatus() == com.forgeos.tools.domain.ToolStatus.FAILED) {
                             // Handle failure / retry logic
                        }
                        break;
                        
                    case DELEGATION_REQUEST:
                        // Suspend this execution, start child agent
                        execution.setStatus(AgentExecutionStatus.WAITING_FOR_AGENT);
                        break;
                        
                    case FAIL:
                        execution.setStatus(AgentExecutionStatus.FAILED);
                        finalResult.setStatus(AgentExecutionStatus.FAILED);
                        finalResult.setErrorMessage(decision.getReasoning());
                        return finalResult;
                }
            }
        } catch (Exception e) {
            execution.setStatus(AgentExecutionStatus.FAILED);
            finalResult.setStatus(AgentExecutionStatus.FAILED);
            finalResult.setErrorMessage(e.getMessage());
        }

        return finalResult;
    }
}
