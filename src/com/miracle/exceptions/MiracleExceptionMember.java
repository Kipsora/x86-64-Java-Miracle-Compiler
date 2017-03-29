package com.miracle.exceptions;

public class MiracleExceptionMember extends MiracleException {
    private final String type;
    private final String identifier;

    public MiracleExceptionMember(String type, String identifier) {
        this.type = type;
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        return "type \"" + type + "\" has no member of \"" + identifier + "\".";
    }
}
