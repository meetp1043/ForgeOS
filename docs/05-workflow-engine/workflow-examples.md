# Workflow Examples

These conceptual examples illustrate how the workflow engine coordinates different types of engineering work. They are not executable definitions — they show the logical flow.

---

## 1. New Web Application
**Trigger**: User says "Build me a portfolio website with a contact form."
**Activated Agents**: Product Manager, Frontend Engineer, QA, Technical Writer.
**Flow**: Requirements → Architecture (simple) → Frontend Implementation → Testing → Staging → Human Approval → Production → Docs.
**Parallelism**: None (simple project).

## 2. New Spring Boot Backend
**Trigger**: User says "Build an inventory management REST API."
**Activated Agents**: BA, PM, Architect, Backend Eng, DB Eng, QA, Code Review, Security, DevOps, Tech Writer.
**Flow**: BA → PM → Architect → [Backend Eng ∥ DB Eng] → Code Review → QA → Security → Staging → Approval → Production.
**Parallelism**: Backend and Database work execute in parallel.

## 3. Feature Addition
**Trigger**: PM creates a feature ticket.
**Flow**: Requirement Refinement → Architecture Assessment → Implementation → Code Review → Testing → Staging → Approval → Production.

## 4. Bug Fix
**Trigger**: QA reports defect.
**Flow**: Bug Triage → Root Cause → Fix → Code Review → Regression Test → Staging → Production.
**Note**: Skips full architecture and requirements phases.

## 5. Failed Test
**Trigger**: QA step reports test failure.
**Flow**: QA Agent emits `StepFailed` → Orchestrator routes failure back to implementing agent → Agent fixes code → Re-runs QA → If still failing after 3 attempts, escalates to Engineering Manager.

## 6. Failed Deployment
**Trigger**: DevOps step reports deployment failure.
**Flow**: Deployment fails → Compensation triggers automatic rollback → SRE Agent validates health of previous version → Incident report generated → Human notified.

## 7. Security Vulnerability
**Trigger**: Security Engineer detects CVE in dependency.
**Flow**: Security Review triggers → Dependency Upgrade workflow spawned → Fix implemented → Testing → Security re-scan → Approval → Patch deployed.

## 8. Production Incident
**Trigger**: SRE monitoring detects anomaly.
**Flow**: Alert Triage → Incident Classification → Immediate Mitigation (e.g., scale up) → Root Cause Analysis → Fix → Verification → Post-Mortem.

## 9. Dependency Upgrade
**Trigger**: Scheduled scan or advisory.
**Flow**: Scan → Upgrade Proposal → Implementation → Full Test Suite → Security Review → Approval → Merge → Deploy.

## 10. Architecture Change
**Trigger**: Architect proposes migration (e.g., adding Redis caching).
**Flow**: ADR Drafted → Peer Review → Human Approval → Implementation Plan → Phased Implementation → Testing → Documentation → Deployment.
