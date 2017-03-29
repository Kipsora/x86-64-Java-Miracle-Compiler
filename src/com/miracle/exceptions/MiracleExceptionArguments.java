package com.miracle.exceptions;

public class MiracleExceptionArguments extends MiracleException {
    private final String typeOrigin;
    private final String typeActual;

    public MiracleExceptionArguments(String typeOrigin, String typeActual) {
        this.typeOrigin = typeOrigin;
        this.typeActual = typeActual;
    }

    @Override
    public String toString() {
        return "the function is declared to receive type of \"" + typeOrigin + "\", but given \""
                + typeActual + ".";
    }
}
