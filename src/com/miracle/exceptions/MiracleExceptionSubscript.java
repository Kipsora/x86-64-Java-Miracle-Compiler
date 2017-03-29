package com.miracle.exceptions;

public class MiracleExceptionSubscript extends MiracleException {
    @Override
    public String toString() {
        return "only array type can be associated with subscription methods.";
    }
}
