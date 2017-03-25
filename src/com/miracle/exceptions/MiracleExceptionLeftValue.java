package com.miracle.exceptions;

public class MiracleExceptionLeftValue extends MiracleException {
    private String expression;

    public MiracleExceptionLeftValue(int line, int column, String expression) {
        super(line, column);
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "expression " + expression + " cannot be used as left-value.";
    }
}
