package com.miracle.exceptions;

public class MiracleExceptionSubscriptContent extends MiracleException {
    private final String type;

    public MiracleExceptionSubscriptContent(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "subscription method requires \"int\", but type \"" + type + "\" was found.";
    }
}
