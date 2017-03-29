package com.miracle.exceptions;

public class MiracleExceptionReturn extends MiracleException {
    private final String typeOrigin;
    private final String typeActual;

    public MiracleExceptionReturn(String typeOrigin, String typeActual) {
        this.typeOrigin = typeOrigin;
        this.typeActual = typeActual;
    }

    @Override
    public String toString() {
        return "the function is declared to return type \""
                + typeOrigin + "\", but \"" + typeActual + "\" was found.";
    }
}
