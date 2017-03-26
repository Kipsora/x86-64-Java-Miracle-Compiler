package com.miracle.exceptions;

public class MiracleExceptionConflictWithKeyword extends MiracleException {
    private String identifier;
    public MiracleExceptionConflictWithKeyword(String identifier) {
        this.identifier = identifier;
    }
    @Override
    public String toString() {
        return "\"" + identifier + "\" is a built-in keyword.";
    }
}
