package com.miracle.exceptions;

public class MiracleExceptionSyntaxError extends MiracleException {
    private String message;
    public MiracleExceptionSyntaxError(int line, int column, String message) {
        super(line, column);
        this.message = message;
    }
    @Override
    public String toString() {
        return message + ".";
    }
}
