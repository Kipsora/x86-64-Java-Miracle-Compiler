package com.miracle.exceptions;

public class MiracleExceptionSyntaxError extends MiracleException {
    private String message;

    public MiracleExceptionSyntaxError(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message + ".";
    }
}
