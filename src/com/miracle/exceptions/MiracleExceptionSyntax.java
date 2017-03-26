package com.miracle.exceptions;

public class MiracleExceptionSyntax extends MiracleException {
    private final String message;

    public MiracleExceptionSyntax(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message + ".";
    }
}
