package com.forgeos.team.domain;

import java.util.UUID;

public class AgentMessage {
    private UUID messageId;
    private UUID senderAgentId;
    private UUID receiverAgentId;
    private String type; // e.g., HANDOFF, WARNING
    private String payload;

    public AgentMessage(UUID messageId, UUID senderAgentId, UUID receiverAgentId, String type, String payload) {
        this.messageId = messageId;
        this.senderAgentId = senderAgentId;
        this.receiverAgentId = receiverAgentId;
        this.type = type;
        this.payload = payload;
    }

    public UUID getMessageId() { return messageId; }
    public UUID getSenderAgentId() { return senderAgentId; }
    public UUID getReceiverAgentId() { return receiverAgentId; }
    public String getType() { return type; }
    public String getPayload() { return payload; }
}
