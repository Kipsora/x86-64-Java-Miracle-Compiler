package com.miracle.scanner.environment.identifier;

public class MiracleIdentifierVariable extends MiracleIdentifier {
    private final String type;
    private final boolean coverable;
    private final String decorator;

    public MiracleIdentifierVariable(String decorator, boolean coverable, String type) {
        this.coverable = coverable;
        this.type = type;
        this.decorator = decorator;
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

    public String getDecorator() {
        return decorator;
    }
}
