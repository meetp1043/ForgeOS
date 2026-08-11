package com.forgeos.agent.application;

import com.forgeos.agent.domain.ExecutionStatus;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.Map;

@Service
public class StateTransitionEngine {

    private static final Map<ExecutionStatus, EnumSet<ExecutionStatus>> VALID_TRANSITIONS = Map.ofEntries(
            Map.entry(ExecutionStatus.CREATED, EnumSet.of(ExecutionStatus.INITIALIZING, ExecutionStatus.CANCELLED)),
            Map.entry(ExecutionStatus.INITIALIZING, EnumSet.of(ExecutionStatus.PLANNING, ExecutionStatus.FAILED, ExecutionStatus.CANCELLED)),
            Map.entry(ExecutionStatus.PLANNING, EnumSet.of(ExecutionStatus.EXECUTING, ExecutionStatus.FAILED, ExecutionStatus.CANCELLED)),
            Map.entry(ExecutionStatus.EXECUTING, EnumSet.of(
                    ExecutionStatus.WAITING_FOR_TOOL,
                    ExecutionStatus.WAITING_FOR_APPROVAL,
                    ExecutionStatus.WAITING_FOR_DEPENDENCY,
                    ExecutionStatus.OBSERVING,
                    ExecutionStatus.VALIDATING,
                    ExecutionStatus.FAILED,
                    ExecutionStatus.CANCELLED,
                    ExecutionStatus.TIMED_OUT,
                    ExecutionStatus.PAUSED
            )),
            Map.entry(ExecutionStatus.WAITING_FOR_TOOL, EnumSet.of(ExecutionStatus.OBSERVING, ExecutionStatus.FAILED, ExecutionStatus.TIMED_OUT, ExecutionStatus.CANCELLED)),
            Map.entry(ExecutionStatus.WAITING_FOR_APPROVAL, EnumSet.of(ExecutionStatus.EXECUTING, ExecutionStatus.FAILED, ExecutionStatus.CANCELLED)),
            Map.entry(ExecutionStatus.OBSERVING, EnumSet.of(ExecutionStatus.EXECUTING, ExecutionStatus.REPLANNING, ExecutionStatus.VALIDATING, ExecutionStatus.FAILED, ExecutionStatus.CANCELLED)),
            Map.entry(ExecutionStatus.REPLANNING, EnumSet.of(ExecutionStatus.EXECUTING, ExecutionStatus.FAILED, ExecutionStatus.CANCELLED)),
            Map.entry(ExecutionStatus.VALIDATING, EnumSet.of(ExecutionStatus.COMPLETING, ExecutionStatus.REPLANNING, ExecutionStatus.FAILED, ExecutionStatus.CANCELLED)),
            Map.entry(ExecutionStatus.COMPLETING, EnumSet.of(ExecutionStatus.COMPLETED, ExecutionStatus.FAILED)),
            Map.entry(ExecutionStatus.PAUSED, EnumSet.of(ExecutionStatus.EXECUTING, ExecutionStatus.CANCELLED)),
            Map.entry(ExecutionStatus.COMPLETED, EnumSet.noneOf(ExecutionStatus.class)),
            Map.entry(ExecutionStatus.FAILED, EnumSet.noneOf(ExecutionStatus.class)),
            Map.entry(ExecutionStatus.CANCELLED, EnumSet.noneOf(ExecutionStatus.class)),
            Map.entry(ExecutionStatus.TIMED_OUT, EnumSet.noneOf(ExecutionStatus.class)),
            Map.entry(ExecutionStatus.QUARANTINED, EnumSet.noneOf(ExecutionStatus.class))
    );

    public void validateTransition(ExecutionStatus current, ExecutionStatus next) {
        if (!VALID_TRANSITIONS.getOrDefault(current, EnumSet.noneOf(ExecutionStatus.class)).contains(next)) {
            throw new IllegalStateException("Invalid state transition from " + current + " to " + next);
        }
    }
}
