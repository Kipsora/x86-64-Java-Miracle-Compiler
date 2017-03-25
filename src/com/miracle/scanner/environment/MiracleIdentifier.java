package com.miracle.scanner.environment;

public abstract class MiracleIdentifier {
    private final boolean coverable;

    protected MiracleIdentifier() {
        this.coverable = true;
    }
    protected MiracleIdentifier(boolean coverable) {
        this.coverable = coverable;
    }

    public abstract String getIdentifierType();

    public boolean getCoverable() {
        return coverable;
    }
}

