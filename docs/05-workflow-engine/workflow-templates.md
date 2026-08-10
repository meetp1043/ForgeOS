# Workflow Templates

Workflow Templates are reusable, pre-defined workflow definitions for common software engineering activities.

## Template Catalog

### 1. New Project
**Trigger**: User creates a new project.
**Steps**: Business Analysis → Requirements → Architecture → Architecture Review → Project Plan → Implementation → Code Review → Testing → Security Review → Staging → Human Approval → Production → Documentation.

### 2. Feature Development
**Trigger**: Product Manager creates a new feature ticket.
**Steps**: Requirement Refinement → Architecture Assessment → Implementation → Code Review → Testing → Security Scan → Staging Deploy → Human Approval → Production Deploy → Documentation Update.

### 3. Bug Fix
**Trigger**: QA or human reports a defect.
**Steps**: Bug Triage → Root Cause Analysis → Fix Implementation → Code Review → Regression Testing → Staging Deploy → Production Deploy.

### 4. Security Review
**Trigger**: Scheduled or on-demand.
**Steps**: Dependency Scan → Static Analysis → Threat Model Review → Security Report → Remediation Tasks.

### 5. Release
**Trigger**: Project Manager initiates a release.
**Steps**: Version Bump → Changelog Generation → Final QA → Security Audit → Release Approval → Production Deploy → Release Notes → Notification.

### 6. Deployment
**Trigger**: Code merged to release branch.
**Steps**: Build → Smoke Tests → Staging Deploy → Integration Tests → Human Approval → Production Deploy → Health Check → Rollback Check.

### 7. Incident Response
**Trigger**: SRE detects an anomaly.
**Steps**: Alert Triage → Incident Classification → Immediate Mitigation → Root Cause Analysis → Fix → Verification → Post-Mortem Report.

### 8. Dependency Upgrade
**Trigger**: Scheduled or security advisory.
**Steps**: Dependency Scan → Upgrade Proposal → Implementation → Testing → Security Review → Approval → Merge.

### 9. Documentation Update
**Trigger**: Feature completion or manual request.
**Steps**: Identify Gaps → Draft Documentation → Review → Publish.

### 10. Architecture Change
**Trigger**: Architect proposes a structural change.
**Steps**: ADR Drafted → Peer Review → Human Approval → Implementation Plan → Phased Implementation → Verification → Documentation.
