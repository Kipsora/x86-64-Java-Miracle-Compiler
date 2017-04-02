package com.miracle.exceptions;

/**
 * Created by kipsora on 17-4-2.
 */
public class MiracleExceptionMainType extends MiracleException {
    private final String type;

    public MiracleExceptionMainType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "the function main should return int, but \"" + type + "\" was found.";
    }
}
