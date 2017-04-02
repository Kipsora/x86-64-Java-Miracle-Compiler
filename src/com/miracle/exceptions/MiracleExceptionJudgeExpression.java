package com.miracle.exceptions;

public class MiracleExceptionJudgeExpression extends MiracleException {
    private final String type;

    public MiracleExceptionJudgeExpression(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "the return type of the expression here must be bool, but type \""
                + type + "\" was found.";
    }
}
