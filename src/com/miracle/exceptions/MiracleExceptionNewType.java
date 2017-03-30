package com.miracle.exceptions;

/**
 * Created by kipsora on 17-3-30.
 */
public class MiracleExceptionNewType extends MiracleException {
    private final String type;

    public MiracleExceptionNewType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "type \"" + type + "\" is not able to declared as an array.";
    }
}
