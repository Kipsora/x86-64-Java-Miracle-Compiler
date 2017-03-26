package com.miracle.exceptions;

public class MiracleExceptionType extends MiracleException {
    private final String typeLeft;
    private final String typeRight;

    public MiracleExceptionType(String typeLeft, String typeRight) {
        this.typeLeft = typeLeft;
        this.typeRight = typeRight;
    }

    @Override
    public String toString() {
        return "Cannot convert \"" + typeLeft + "\" to \"" + typeRight + "\".";
    }
}
