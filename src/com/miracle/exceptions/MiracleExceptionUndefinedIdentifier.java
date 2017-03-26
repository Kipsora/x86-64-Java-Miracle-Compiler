package com.miracle.exceptions;

public class MiracleExceptionUndefinedIdentifier extends MiracleException {
    private final String identifier;

    public MiracleExceptionUndefinedIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        return "identifier \"" + identifier + "\" was not in the environment.";
    }

}

