package com.miracle.scanner.environment.identifier;

public class MiracleIdentifierVariable extends MiracleIdentifier {
    private final String type;

    public MiracleIdentifierVariable(boolean coverable, String type) {
        super(coverable);
        this.type = type;
    }

    public MiracleIdentifierVariable(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String getIdentifierType() {
        return "variable";
    }
}
