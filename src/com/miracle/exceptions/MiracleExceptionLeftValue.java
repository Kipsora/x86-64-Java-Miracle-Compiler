package com.miracle.exceptions;

import org.antlr.v4.runtime.Token;

public class MiracleExceptionLeftValue extends MiracleException {
    private String expression;

    public MiracleExceptionLeftValue(int line, int column, String expression) {
        super(line, column);
        this.expression = expression;
    }
    public MiracleExceptionLeftValue(Token token, String expression) {
        super(token);
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "expression " + expression + " cannot be used as left-value.";
    }
}
