package com.miracle.exceptions;

import org.antlr.v4.runtime.Token;

public class MiracleExceptionDuplicateDeclaration extends MiracleException {
    private String type;
    private String identifier;

    public MiracleExceptionDuplicateDeclaration(int line, int column, String type, String identifier) {
        super(line, column);
        this.type = type;
        this.identifier = identifier;
    }

    public MiracleExceptionDuplicateDeclaration(Token token, String type, String identifier) {
        super(token);
        this.type = type;
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        return "duplicated declaration of " + type + " " + identifier + " was found.";
    }
}
