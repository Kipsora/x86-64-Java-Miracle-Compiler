package com.miracle.exceptions;

import org.antlr.v4.runtime.Token;

public class MiracleExceptionDeclaration extends MiracleException{
    private String type;
    public MiracleExceptionDeclaration(int line, int column, String type) {
        super(line, column);
        this.type = type;
    }
    public MiracleExceptionDeclaration(Token token, String type) {
        super(token);
        this.type = type;
    }

    @Override
    public String toString() {
        return "the " + type + " declaration cannot be placed here.";
    }
}
