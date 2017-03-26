package com.miracle.exceptions;

public class MiracleExceptionSpecialExpression extends MiracleException {
    private final String type;
    private final String need;
    private final String name;

    public MiracleExceptionSpecialExpression(String name, String need, String type) {
        this.type = type;
        this.need = need;
        this.name = name;
    }

    @Override
    public String toString() {
        return "only \"" + need + "\" can be used in " + name + " expressions, but \" type \""
                + type + "\"  was found.";
    }
}
