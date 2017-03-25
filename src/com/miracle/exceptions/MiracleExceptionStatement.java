package com.miracle.exceptions;

public class MiracleExceptionStatement extends MiracleException {
    private final String type;

    public MiracleExceptionStatement(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "the \"" + type + "\" statement cannot be placed here.";
    }
}
