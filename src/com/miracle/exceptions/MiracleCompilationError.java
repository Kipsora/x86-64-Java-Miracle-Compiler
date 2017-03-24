package com.miracle.exceptions;

import org.antlr.v4.runtime.Token;

/**
 * Created by kipsora on 17-3-24.
 */
public class MiracleCompilationError extends Error {
    private int line;
    private int column;
    private String message;

    public MiracleCompilationError(int line, int column, String message) {
        this.message = message;
        this.line = line;
        this.column = column;
    }
    public MiracleCompilationError(Token token, String message) {
        this.message = message;
        this.line = token.getLine();
        this.column = token.getCharPositionInLine() + 1;
    }

    public String getMessage() {
        return "Compilation Error: " + String.valueOf(line) + ":" + String.valueOf(column) + ": " + message;
    }
}
