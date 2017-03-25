package com.miracle.exceptions;

public class MiracleExceptionLeftValue extends MiracleException {
    private final String expression;

    public MiracleExceptionLeftValue(String expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "expression \"" + expression + "\" cannot be used as left-value.";
    }
}
