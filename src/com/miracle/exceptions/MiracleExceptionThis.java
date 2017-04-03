package com.miracle.exceptions;

public class MiracleExceptionThis extends MiracleException {
    @Override
    public String toString() {
        return "unexpected keyword \"this\".";
    }
}
