package com.miracle.exceptions;

public class MiracleExceptionConflictWithBuiltin extends MiracleException {
    private String identifier;
    public MiracleExceptionConflictWithBuiltin(String identifier) {
        this.identifier = identifier;
    }
    @Override
    public String toString() {
        return "\"" + identifier + "\" is a built-in method.";
    }
}
