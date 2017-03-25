package com.miracle.exceptions;

import org.antlr.v4.runtime.Token;

public class MiracleExceptionSyntaxError extends MiracleException {
    private String message;

    public MiracleExceptionSyntaxError(int line, int column, String message) {
        super(line, column);
        this.message = message;
    }

    public MiracleExceptionSyntaxError(Token token, String message) {
        super(token);
        this.message = message;
    }

    @Override
    public String toString() {
        return message + ".";
    }
}
