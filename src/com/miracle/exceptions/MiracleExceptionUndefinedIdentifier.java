package com.miracle.exceptions;

import org.antlr.v4.runtime.Token;

public class MiracleExceptionUndefinedIdentifier extends MiracleException {
    private String identifier;

    public MiracleExceptionUndefinedIdentifier(int line, int column, String identifier) {
        super(line, column);
        this.identifier = identifier;
    }

    public MiracleExceptionUndefinedIdentifier(Token token, String identifier) {
        super(token);
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        return "identifier " + identifier + " was not in environment.";
    }

}

