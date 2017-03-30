package com.miracle.exceptions;

public class MiracleExceptionNewBaseType extends MiracleException {
    @Override
    public String toString() {
        return "cannot declare new a single built-in type.";
    }
}
