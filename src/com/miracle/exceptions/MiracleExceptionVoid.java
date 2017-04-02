package com.miracle.exceptions;

public class MiracleExceptionVoid extends MiracleException {
    @Override
    public String toString() {
        return "cannot declare a variable with void type";
    }
}
