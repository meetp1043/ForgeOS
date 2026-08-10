# Memory Correction

Unlike a standard update (where the facts on the ground changed, requiring a new version), a **Correction** occurs when the memory was objectively wrong at the time of creation (e.g., a hallucination or parsing error).

Users and authorized agents must be able to correct incorrect memory to prevent the AI from repeatedly making the same mistake.

## Correction Flow

1. **Correction Request**: A human user or a peer-review agent flags a memory entry as factually incorrect.
2. **Verification**: If requested by a human, the correction is implicitly trusted. If requested by an agent, the correction may require a consensus check or human approval depending on the memory's `Importance`.
3. **Replacement**: The erroneous content is replaced. Unlike the standard update policy, a correction *can* overwrite the payload to prevent the hallucinated data from contaminating the vector space, provided an audit trail is maintained.
4. **Audit**: The transaction is logged: "Memory [UUID] corrected by [Actor]. Old value: [Hash/Summary]. Reason: Factually incorrect."
5. **Downstream Impact**: The system invalidates cached context assemblies or active agent working memories that relied on the corrected entry.

## Agent Guidelines
Agents must be trained to recognize when they are failing due to a bad memory retrieval and proactively emit a `CorrectionRequest` event.
