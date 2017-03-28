package com.miracle.exceptions;

public class MiracleExceptionCompareExpression extends MiracleException {
    private final String typeA;
    private final String typeB;

    public MiracleExceptionCompareExpression(String typeA, String typeB) {
        this.typeA = typeA;
        this.typeB = typeB;
    }

    @Override
    public String toString() {
        return "type \"" + typeA + "\" and type \"" + typeB + "\" is not comparable.";
    }
}
