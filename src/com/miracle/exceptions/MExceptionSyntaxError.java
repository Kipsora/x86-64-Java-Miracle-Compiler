package com.miracle.exceptions;

public class MExceptionSyntaxError extends MException {
    private String message;
    public MExceptionSyntaxError(int line, int column, String message) {
        super(line, column);
        this.message = message;
    }
    @Override
    public String toString() {
        return message + ".";
    }
}
