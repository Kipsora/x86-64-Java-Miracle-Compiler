package com.miracle.exceptions;

public class MiracleExceptionLeftValue extends MiracleException {
    @Override
    public String toString() {
        return "expression here cannot be used as left-value.";
    }
}
