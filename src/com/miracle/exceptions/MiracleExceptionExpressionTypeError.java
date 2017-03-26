package com.miracle.exceptions;

public class MiracleExceptionExpressionTypeError extends MiracleException {
    private final String operator;
    private final String typeA;
    private final String typeB;

    public MiracleExceptionExpressionTypeError(String typeA, String operator, String typeB) {
        this.operator = operator;
        this.typeA = typeA;
        this.typeB = typeB;
    }

    @Override
    public String toString() {
        return "use \"" + operator + "\" on type \"" + typeA + "\" and type \"" + typeB + "\".";
    }
}
