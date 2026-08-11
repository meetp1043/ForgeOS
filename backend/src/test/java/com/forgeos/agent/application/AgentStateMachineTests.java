package com.forgeos.agent.application;

import com.forgeos.agent.domain.ExecutionStatus;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AgentStateMachineTests {

    private final StateTransitionEngine engine = new StateTransitionEngine();

    @Test
    void validTransitionsSucceed() {
        assertDoesNotThrow(() -> engine.validateTransition(ExecutionStatus.CREATED, ExecutionStatus.INITIALIZING));
        assertDoesNotThrow(() -> engine.validateTransition(ExecutionStatus.PLANNING, ExecutionStatus.EXECUTING));
        assertDoesNotThrow(() -> engine.validateTransition(ExecutionStatus.EXECUTING, ExecutionStatus.WAITING_FOR_TOOL));
        assertDoesNotThrow(() -> engine.validateTransition(ExecutionStatus.OBSERVING, ExecutionStatus.REPLANNING));
        assertDoesNotThrow(() -> engine.validateTransition(ExecutionStatus.VALIDATING, ExecutionStatus.COMPLETING));
    }

    @Test
    void invalidTransitionsFail() {
        assertThrows(IllegalStateException.class, () -> engine.validateTransition(ExecutionStatus.COMPLETED, ExecutionStatus.EXECUTING));
        assertThrows(IllegalStateException.class, () -> engine.validateTransition(ExecutionStatus.FAILED, ExecutionStatus.REPLANNING));
        assertThrows(IllegalStateException.class, () -> engine.validateTransition(ExecutionStatus.CREATED, ExecutionStatus.COMPLETED));
        assertThrows(IllegalStateException.class, () -> engine.validateTransition(ExecutionStatus.EXECUTING, ExecutionStatus.CREATED));
    }
}
