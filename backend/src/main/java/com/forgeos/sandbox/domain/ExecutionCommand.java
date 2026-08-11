package com.forgeos.sandbox.domain;

import java.util.List;

public class ExecutionCommand {
    private String executable;
    private List<String> arguments;
    private String workingDirectory;
    private long timeoutMs;

    public ExecutionCommand(String executable, List<String> arguments, String workingDirectory, long timeoutMs) {
        this.executable = executable;
        this.arguments = arguments;
        this.workingDirectory = workingDirectory;
        this.timeoutMs = timeoutMs;
    }

    public String getExecutable() { return executable; }
    public List<String> getArguments() { return arguments; }
    public String getWorkingDirectory() { return workingDirectory; }
    public long getTimeoutMs() { return timeoutMs; }
}
