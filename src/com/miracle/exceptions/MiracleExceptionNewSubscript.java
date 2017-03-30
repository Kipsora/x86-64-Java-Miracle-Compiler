package com.miracle.exceptions;

public class MiracleExceptionNewSubscript extends MiracleException {
    private final String type;

    public MiracleExceptionNewSubscript(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "the type of the subscription in new method should be integer, but \""
                + type + "\" was found";
    }
}
