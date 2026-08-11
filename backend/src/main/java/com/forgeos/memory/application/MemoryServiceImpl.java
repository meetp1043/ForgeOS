package com.forgeos.memory.application;

import com.forgeos.memory.domain.Memory;
import com.forgeos.memory.domain.MemoryCandidate;
import com.forgeos.memory.domain.MemoryStatus;
import com.forgeos.memory.domain.MemoryAuthority;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.time.OffsetDateTime;

@Service
public class MemoryServiceImpl implements MemoryService {

    private final MemoryValidator validator;

    public MemoryServiceImpl(MemoryValidator validator) {
        this.validator = validator;
    }

    @Override
    public Memory activateCandidate(MemoryCandidate candidate) {
        validator.validate(candidate);

        Memory memory = new Memory();
        memory.setId(UUID.randomUUID());
        memory.setTenantId(candidate.getTenantId());
        memory.setProjectId(candidate.getProjectId());
        memory.setScope(candidate.getScope());
        memory.setType(candidate.getType());
        memory.setTitle(candidate.getTitle());
        memory.setContent(candidate.getContent());
        memory.setSource(candidate.getSource());
        memory.setConfidence(candidate.getConfidence());
        memory.setImportance(candidate.getImportance());
        memory.setSecurityClassification(candidate.getSecurityClassification());
        
        // Starts as Active, but model generated. Requires evidence to be VERIFIED.
        memory.setStatus(MemoryStatus.ACTIVE);
        memory.setAuthority(MemoryAuthority.MODEL_GENERATED);
        memory.setCreatedAt(OffsetDateTime.now());
        
        // DB save would go here
        return memory;
    }

    @Override
    public Memory supersedeMemory(UUID oldMemoryId, Memory newMemory) {
        // Fetch old memory, set status to SUPERSEDED, save newMemory.
        return newMemory;
    }

    @Override
    public void archiveMemory(UUID memoryId) {
        // Fetch memory, set status to ARCHIVED.
    }
}
