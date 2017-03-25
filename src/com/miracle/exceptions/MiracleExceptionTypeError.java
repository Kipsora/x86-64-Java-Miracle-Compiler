package com.miracle.exceptions;

public class MiracleExceptionTypeError extends MiracleException {
    private String typeLeft, typeRight;

    public MiracleExceptionTypeError(String typeLeft, String typeRight) {
        this.typeLeft = typeLeft;
        this.typeRight = typeRight;
    }

    @Override
    public String toString() {
        return "Cannot convert \"" + typeLeft + "\" to " + typeRight + "\".";
    }
}
