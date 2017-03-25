package com.miracle.exceptions;

import org.antlr.v4.runtime.Token;

public abstract class MiracleException extends Error {
    private int line;
    private int column;

    public MiracleException(int line, int column) {
        this.line = line;
        this.column = column;
    }

    public MiracleException(Token token) {
        this.line = token.getLine();
        this.column = token.getCharPositionInLine() + 1;
    }

    public abstract String toString();

    @Override
    public String getMessage() {
        return String.valueOf(line) + ":" + String.valueOf(column) + ": syntax error: " + toString();
    }
}