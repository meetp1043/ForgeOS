#!/usr/bin/env pwsh
# validate-repository.ps1

$ErrorActionPreference = "Stop"

Write-Host "Starting repository validation..."

$RequiredDirectories = @(
    "docs",
    "docs/00-governance",
    "prompts",
    "scripts",
    "templates",
    ".github/workflows"
)

$RequiredFiles = @(
    "README.md",
    "AGENTS.md",
    "docs/README.md",
    "docs/00-governance/README.md",
    "docs/00-governance/engineering-principles.md",
    "docs/00-governance/documentation-standards.md",
    "docs/00-governance/architecture-decision-records.md",
    "docs/00-governance/security-baseline.md",
    "docs/00-governance/git-conventions.md",
    "docs/00-governance/definition-of-done.md",
    "docs/00-governance/change-management.md",
    "templates/architecture-decision-record.md",
    "prompts/README.md",
    "scripts/validate-repository.ps1",
    "scripts/check-secrets.ps1",
    ".github/workflows/repository-validation.yml"
)

$HasErrors = $false

foreach ($Dir in $RequiredDirectories) {
    if (-Not (Test-Path $Dir -PathType Container)) {
        Write-Error "Missing required directory: $Dir"
        $HasErrors = $true
    }
}

foreach ($File in $RequiredFiles) {
    if (-Not (Test-Path $File -PathType Leaf)) {
        Write-Error "Missing required file: $File"
        $HasErrors = $true
    }
}

if ($HasErrors) {
    Write-Host "Repository validation FAILED." -ForegroundColor Red
    exit 1
}

Write-Host "Repository validation PASSED." -ForegroundColor Green
exit 0
