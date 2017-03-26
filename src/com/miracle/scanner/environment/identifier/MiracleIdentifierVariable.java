package com.miracle.scanner.environment.identifier;

public class MiracleIdentifierVariable extends MiracleIdentifier {
    private final String type;
    private final boolean coverable;

    public MiracleIdentifierVariable(boolean coverable, String type) {
        this.coverable = coverable;
        this.type = type;
    }

    public MiracleIdentifierVariable(String type) {
        this.coverable = true;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public boolean getCoverable() {
        return coverable;
    }

    @Override
    public String getIdentifierType() {
        return "variable";
    }
}
