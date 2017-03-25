package com.miracle.exceptions;

public class MiracleExceptionIdentifierTypeError extends MiracleException {
    private final String identifier;
    private final String type;
    private final String expectedType;

    public MiracleExceptionIdentifierTypeError(String identifier, String type, String expectedType) {
        this.identifier = identifier;
        this.type = type;
        this.expectedType = expectedType;
    }

    @Override
    public String toString() {
        return "identifier \"" + identifier + "\" of type \"" + type + "\" was found, but \"" + expectedType + "\" was expected.";
    }
}
