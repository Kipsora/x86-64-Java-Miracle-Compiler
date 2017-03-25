package com.miracle.exceptions;

public class MiracleExceptionStatementScope extends MiracleException {
    private final String type;
    private final String scope;

    public MiracleExceptionStatementScope(String type, String scope) {
        this.type = type;
        this.scope = scope;
    }

    @Override
    public String toString() {
        return "\"" + type + "\" statement should be in a " + scope + " scope.";
    }
}
