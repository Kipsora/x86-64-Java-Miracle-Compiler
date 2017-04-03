package com.miracle.exceptions;

public class MiracleExceptionMultipleConstructor extends MiracleException {
    @Override
    public String toString() {
        return "multiple declarations of constructor were found.";
    }
}
