package com.forgeos.team.application;

import com.forgeos.team.domain.AgentMessage;
import com.forgeos.team.domain.AgentTeam;
import com.forgeos.team.domain.AgentTeamMember;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AgentMessageBroker {

    private final List<AgentMessage> messageStore = new ArrayList<>();

    public void sendMessage(AgentTeam team, AgentMessage message) {
        // Enforce team isolation boundaries
        boolean senderInTeam = isAgentInTeam(team, message.getSenderAgentId());
        boolean receiverInTeam = isAgentInTeam(team, message.getReceiverAgentId());

        if (!senderInTeam || !receiverInTeam) {
            throw new SecurityException("Cross-team or out-of-bounds agent communication is denied.");
        }

        messageStore.add(message);
    }

    public List<AgentMessage> getInbox(UUID agentId) {
        return messageStore.stream()
                .filter(m -> m.getReceiverAgentId().equals(agentId))
                .toList();
    }

    private boolean isAgentInTeam(AgentTeam team, UUID agentId) {
        for (AgentTeamMember member : team.getMembers()) {
            if (member.getAgentId().equals(agentId)) {
                return true;
            }
        }
        return false;
    }
}
