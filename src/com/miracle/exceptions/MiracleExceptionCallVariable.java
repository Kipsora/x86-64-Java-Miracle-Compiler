package com.miracle.exceptions;

public class MiracleExceptionCallVariable extends MiracleException {
    @Override
    public String toString() {
        return "calling method should be only applied to function.";
    }
}
