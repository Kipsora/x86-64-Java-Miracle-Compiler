package com.miracle.exceptions;

import org.antlr.v4.runtime.Token;

public class MiracleExceptionStatement extends MiracleException {
    private String type;

    public MiracleExceptionStatement(int line, int column, String type) {
        super(line, column);
        this.type = type;
    }

    public MiracleExceptionStatement(Token token, String type) {
        super(token);
        this.type = type;
    }

    @Override
    public String toString() {
        return "the \"" + type + "\" statement cannot be placed here.";
    }
}
