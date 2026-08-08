#!/usr/bin/env pwsh
# check-secrets.ps1

$ErrorActionPreference = "Continue"

Write-Host "Starting basic secret scan..."

# Simple patterns for obvious secrets
$SecretPatterns = @(
    "sk-[a-zA-Z0-9]{32,}",                # OpenAI API Key format
    "AKIA[0-9A-Z]{16}",                   # AWS Access Key
    "-----BEGIN (RSA|OPENSSH) PRIVATE KEY-----", # Private SSH Key
    "ghp_[a-zA-Z0-9]{36}"                 # GitHub Personal Access Token
)

$HasSecrets = $false

# Scan all files not in .git
$Files = Get-ChildItem -Path . -Recurse -File | Where-Object { $_.FullName -notmatch "\\.git\\" }

foreach ($File in $Files) {
    foreach ($Pattern in $SecretPatterns) {
        $Matches = Select-String -Path $File.FullName -Pattern $Pattern -Quiet
        if ($Matches) {
            Write-Error "POTENTIAL SECRET FOUND in $($File.FullName) matching pattern $($Pattern)"
            $HasSecrets = $true
        }
    }
}

if ($HasSecrets) {
    Write-Host "Secret scan FAILED." -ForegroundColor Red
    exit 1
}

Write-Host "Secret scan PASSED. No obvious secrets found." -ForegroundColor Green
exit 0
