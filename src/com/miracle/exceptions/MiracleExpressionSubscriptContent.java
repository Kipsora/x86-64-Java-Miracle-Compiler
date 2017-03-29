package com.miracle.exceptions;

public class MiracleExpressionSubscriptContent extends MiracleException {
    private final String type;

    public MiracleExpressionSubscriptContent(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "subscription method requires integer, but type \"" + type + "\" was found.";
    }
}
