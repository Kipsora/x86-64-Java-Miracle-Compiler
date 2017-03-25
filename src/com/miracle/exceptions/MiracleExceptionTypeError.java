package com.miracle.exceptions;

public class MiracleExceptionTypeError extends MiracleException {
    private String typeLeft, typeRight;
    public MiracleExceptionTypeError(int line, int column, String typeLeft, String typeRight) {
        super(line, column);
        this.typeLeft = typeLeft;
        this.typeRight = typeRight;
    }
    @Override
    public String toString() {
        return "Cannot convert \"" + typeLeft + "\" to " + typeRight + "\".";
    }
}
