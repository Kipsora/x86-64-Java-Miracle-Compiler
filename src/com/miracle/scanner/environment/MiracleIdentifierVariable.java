package com.miracle.scanner.environment;

public class MiracleIdentifierVariable extends MiracleIdentifier {
    private final String type;
    private final String value;

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public MiracleIdentifierVariable(String type) {
        this.type = type;
        this.value = null;
    }

    public MiracleIdentifierVariable(String type, String value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String getIdentifierType() {
        return "variable";
    }
}
