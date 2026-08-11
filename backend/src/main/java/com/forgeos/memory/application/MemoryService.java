package com.forgeos.memory.application;

import com.forgeos.memory.domain.Memory;
import com.forgeos.memory.domain.MemoryCandidate;

import java.util.UUID;

public interface MemoryService {
    Memory activateCandidate(MemoryCandidate candidate);
    Memory supersedeMemory(UUID oldMemoryId, Memory newMemory);
    void archiveMemory(UUID memoryId);
}
