package com.miracle.exceptions;

public class MiracleExceptionBinaryExpression extends MiracleException {
    private final String typeA;
    private final String typeB;

    public MiracleExceptionBinaryExpression(String typeA, String typeB) {
        this.typeA = typeA;
        this.typeB = typeB;
    }

    @Override
    public String toString() {
        return "different types(\"" + typeA + "\", \"" + typeB + "\") in both sides of the expression.";
    }
}
