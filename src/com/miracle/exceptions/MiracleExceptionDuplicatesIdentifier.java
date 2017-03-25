package com.miracle.exceptions;

import org.antlr.v4.runtime.Token;

class MiracleExceptionDuplicatesIdentifier extends MiracleException {
    private String identifier;
    public MiracleExceptionDuplicatesIdentifier(int line, int column, String identifier) {
        super(line, column);
        this.identifier = identifier;
    }
    public MiracleExceptionDuplicatesIdentifier(Token token, String identifier) {
        super(token);
        this.identifier = identifier;
    }
    @Override
    public String toString() {
        return "duplicated identifier " + identifier + " was found.";
    }
}
