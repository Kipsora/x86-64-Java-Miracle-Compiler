package com.miracle.exceptions;

public class MiracleExceptionType extends MiracleException {
    private final String operator;
    private final String typeA;
    private final String typeB;

    public MiracleExceptionType(String operator, String typeA, String typeB) {
        this.operator = operator;
        this.typeA = typeA;
        this.typeB = typeB;
    }

    @Override
    public String toString() {
        return "cannot take \"" + operator + "\" method between \"" + typeA + "\" and \"" + typeB + "\".";
    }
}
