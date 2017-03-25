package com.miracle.exceptions;

public class MiracleExceptionSyntaxError extends MiracleException {
    private final String message;

    public MiracleExceptionSyntaxError(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message + ".";
    }
}
