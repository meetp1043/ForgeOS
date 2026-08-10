# Context Examples

The following examples illustrate how the Context Engine assembles the Context Package for specific, real-world engineering scenarios.

---

## EXAMPLE 1: FRONTEND BUG

**Task**: "Fix the login button not responding."
**Agent Role**: Frontend Engineer

**Context Should Include**:
- Current task description.
- UI requirements for the login page.
- Relevant frontend files (`Login.tsx`, `AuthButton.tsx`).
- Authentication API contract (OpenAPI spec for `/login`).
- Related component styles (`login.css`).
- Relevant tests (`Login.test.tsx`).
- Recent relevant Git changes (e.g., "Someone refactored the Auth handler yesterday").
- Previous failure logs if available (e.g., "Jest test failing on 'Button should trigger API'").

**Do NOT Include**:
- Unrelated backend modules (e.g., `PaymentService.java`).
- Unrelated cloud configuration (e.g., Terraform scripts).
- Production secrets.

---

## EXAMPLE 2: BACKEND API

**Task**: "Add POST /api/orders."
**Agent Role**: Backend Engineer

**Context Should Include**:
- The exact business requirement.
- API conventions (e.g., "All responses must be wrapped in a standard `ApiResponse` envelope").
- Architecture (ADR: "Use Spring Boot 3").
- Relevant backend module (`OrderController`, `OrderService`).
- Database model (`OrderEntity`, schema).
- Security rules ("Requires JWT with `order:write` scope").
- Validation rules.
- Existing tests.

---

## EXAMPLE 3: DATABASE MIGRATION

**Task**: "Migrate user.age to user.date_of_birth."
**Agent Role**: Database Engineer

**Context Should Include**:
- Approved schema design document.
- Current schema definition for the `users` table.
- Migration history (to ensure correct sequential numbering like Flyway/Liquibase).
- Affected services (which backend services query this table?).
- Test requirements.
- Rollback/compensation policy.
- Approval requirements ("Must have lead DBA approval").

---

## EXAMPLE 4: ARCHITECTURE REVIEW

**Task**: "Review the proposed caching strategy."
**Agent Role**: Solution Architect

**Context Should Include**:
- The high-level requirements.
- Current architecture diagrams.
- Existing ADRs (e.g., "We already use Redis for session state").
- Constraints (e.g., "Maximum AWS spend is $1000/mo").
- Scalability requirements.
- Security requirements.
- Existing technology decisions.

---

## EXAMPLE 5: PRODUCTION INCIDENT

**Task**: "Investigate 500 errors on checkout."
**Agent Role**: SRE Engineer

**Context Should Include**:
- Current incident alerts (Datadog/PagerDuty payload).
- Affected service (`checkout-service`).
- Recent deployment (What went out in the last 2 hours?).
- Recent Git changes (diff of the last deploy).
- Live logs.
- Metrics.
- Traces.
- Runbooks for checkout failures.
- Known previous incidents with similar signatures.
*(This context must prioritize real-time freshness above all else).*

---

## EXAMPLE 6: SECURITY VULNERABILITY

**Task**: "Patch CVE-2024-XXXX in Jackson Databind."
**Agent Role**: Security Engineer

**Context Should Include**:
- The specific vulnerability description.
- The affected dependency (`pom.xml` or `build.gradle`).
- Affected code (where is Jackson used?).
- Security policy (how to bump versions, approval paths).
- Deployment state.
- Severity.
- Remediation history.
